/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.backend.dto.AssessmentDTO;
import app.backend.dto.AssessmentFieldsDTO;
import app.backend.exception.RealestateException;
import app.backend.model.Appointment;
import app.backend.model.Assessment;
import app.backend.model.LongRentAssessment;
import app.backend.model.PlaceDescription;
import app.backend.model.PlaceInventory;
import app.backend.model.TemporaryRentAssessment;
import app.backend.model.enums.AppointmentStatus;
import app.backend.model.enums.EstateOperations;
import app.backend.repository.AssessmentRepository;
import app.backend.utils.ErrorBuffer;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@Component
public class AssessmentService extends ArenaService<Assessment,AssessmentDTO>{
	
	@Autowired
	AssessmentRepository assessmentRepository;
	
	@Autowired
	AppointmentService appointmentService;

	@Autowired
	PlaceDescriptionService placeDescriptionService;
	
	@Autowired
	LongRentAssessmentService longRentAssessmentService;
	
	@Autowired
	TemporaryRentAssessmentService temporaryRentAssessmentService;

	@Autowired
	private PlaceInventoryService placeInventoryService;
	
	@Override
	protected JpaRepository<Assessment, Long> getRepository() {
		return assessmentRepository;
	}

	@Override
	@Transactional
	public Assessment create(Optional<AssessmentDTO> ent) {
		
		ErrorBuffer errors = new ErrorBuffer();
		AssessmentDTO assessmentDto = ent.get();
		
		Assessment assessment =  new Assessment();
		BeanUtils.copyProperties(assessmentDto, assessment);
		errors.append("assessment", this.validate(assessment));
		PlaceDescription placeDescription = assessmentDto.getPlaceDescription();
		errors.append("placeDescription", placeDescriptionService.validate(placeDescription));
		
		PlaceInventory placeInventory = assessmentDto.getPlaceInventory();
		errors.append("placeInventory", placeInventoryService.validate(placeInventory));
		
		
		Assessment saleAssessment = null;
		if(assessmentDto.getSaleSuggestedValue()!=null) {
			saleAssessment=new Assessment();
			BeanUtils.copyProperties((AssessmentFieldsDTO) assessmentDto, saleAssessment);
			saleAssessment.setOperation(EstateOperations.SALE);
			saleAssessment.setSugestedValue(assessmentDto.getSaleSuggestedValue());
		}
		
		Assessment longRentAssessment = null;
		if(assessmentDto.getLongRentAssessment()!=null) {
			longRentAssessment=new Assessment();
			BeanUtils.copyProperties((AssessmentFieldsDTO) assessmentDto, longRentAssessment);
			longRentAssessment.setOperation(EstateOperations.LONG_RENT);
			errors.append("longRentAssessment", longRentAssessmentService.validate(assessmentDto.getLongRentAssessment()));
			if(errors.getErrors().length==0) {
				LongRentAssessment lrAssessment = longRentAssessmentService.create(Optional.of(assessmentDto.getLongRentAssessment()));
				longRentAssessment.setLongRentAssessment(lrAssessment.getId());
				longRentAssessment.setSugestedValue(lrAssessment.getMontlySuggestedPrice());
			}
		}
			
		Assessment assessmentForTemporaryRent = null;
		if(assessmentDto.getTemporaryRentAssessment()!=null) {
			assessmentForTemporaryRent = new Assessment();
			BeanUtils.copyProperties((AssessmentFieldsDTO) assessmentDto, assessmentForTemporaryRent);
			assessmentForTemporaryRent.setOperation(EstateOperations.RENT);
			errors.append("temporaryRentAssessment", temporaryRentAssessmentService.validate(assessmentDto.getTemporaryRentAssessment()));
			if(errors.getErrors().length==0) {
				TemporaryRentAssessment trAssessment = this.temporaryRentAssessmentService.create(
						Optional.of(assessmentDto.getTemporaryRentAssessment()));
				assessmentForTemporaryRent.setTemporaryRentAssessment(trAssessment.getId());
				assessmentForTemporaryRent.setSugestedValue(trAssessment.getSuggestedStayPrice());
			}
		}
		
	
		if(errors.getErrors().length>0) {
			throw new RealestateException(errors.getErrors());
		}
		PlaceDescription description = placeDescriptionService.create(
				Optional.of(placeDescription));
		PlaceInventory inventory = placeInventoryService.create(
				Optional.of(placeInventory));
		
		
		updateAppointment(assessmentDto.getPlaceId(), assessment.getOperation());

		saleAssessment = this.create(saleAssessment, description, inventory);
		longRentAssessment = this.create(longRentAssessment, description, inventory);
		assessmentForTemporaryRent = this.create(assessmentForTemporaryRent, description, inventory);
		
		return ObjectUtils.firstNonNull(saleAssessment, assessmentForTemporaryRent, longRentAssessment);
	}

	private Assessment create(Assessment assessment, PlaceDescription description, PlaceInventory inventory) {
		if(assessment==null)
			return null;
		assessment.setPlaceDescription(description.getId());
		assessment.setPlaceInventory(inventory.getId());
		assessment.setEstateCreated(false);
		assessment = this.getRepository().save(assessment);
		return assessment;
	}

	private void updateAppointment(Long placeId, EstateOperations operation) {
		Appointment a = new Appointment();
		a.setPlaceId(placeId);
		a.setOperation(operation);
		List<Appointment> apps = appointmentService.get(a);
		if(!apps.isEmpty()) {
			Appointment first = apps.get(0);
			first.setStatus(AppointmentStatus.CONCRETED);
			appointmentService.update(first);
		}
	}

	@Override
	public List<Assessment> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public AssessmentDTO buildTemplate(Map<String, String> parameters) {
		try {
			AssessmentDTO assessment = new AssessmentDTO();
			Long appointment = Long.valueOf(parameters.get("appointment"));
			Appointment app = appointmentService.get(appointment);
			if(app!=null) {
				BeanUtils.copyProperties(app, assessment,"id");
			}
			return assessment;
		}catch (Exception e) {
			return super.buildTemplate(parameters);
		}
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Assessment.class);
	}
	
}
