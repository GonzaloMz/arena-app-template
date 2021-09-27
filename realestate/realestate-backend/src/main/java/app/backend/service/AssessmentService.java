/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.backend.exception.RealestateException;
import app.backend.model.Appointment;
import app.backend.model.Assessment;
import app.backend.model.PlaceDescription;
import app.backend.model.dto.AssessmentDTO;
import app.backend.model.dto.AssessmentFieldsDTO;
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
	
	
	@Override
	protected JpaRepository<Assessment, Long> getRepository() {
		return assessmentRepository;
	}

	@Override
	public Assessment create(Optional<AssessmentDTO> ent) {
		Assessment assessment =  new Assessment();
		BeanUtils.copyProperties(ent.get(), assessment);
		BeanUtils.copyProperties((AssessmentFieldsDTO) ent.get(), assessment);
		ErrorBuffer errors = new ErrorBuffer();
		errors.append("assessment", this.validate(assessment));
		PlaceDescription placeDescription = ent.get().getPlaceDescription();
		errors.append("placeDescription", placeDescriptionService.validate(placeDescription));
		if(errors.getErrors().length>0) {
			throw new RealestateException(errors.getErrors());
		}
		PlaceDescription description = placeDescriptionService.create(
				Optional.of(placeDescription));
		updateAppointment(assessment.getPlaceId(), assessment.getOperation());
		assessment.setPlaceDescription(description.getId());
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
