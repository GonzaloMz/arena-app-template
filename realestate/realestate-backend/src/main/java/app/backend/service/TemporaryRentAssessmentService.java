/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.backend.dto.TemporaryRentAssessmentDTO;
import app.backend.model.TemporaryRentAssessment;
import app.backend.model.TemporaryRentFacilities;
import app.backend.repository.TemporaryRentAssessmentRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@Component
public class TemporaryRentAssessmentService extends ArenaService<TemporaryRentAssessment,TemporaryRentAssessmentDTO>{
	
	@Autowired
	TemporaryRentAssessmentRepository temporaryRentAssessmentRepository;
	
	@Autowired
	TemporaryRentFacilitiesService temporaryRentFacilitiesService;
	
	@Autowired
	ObjectMapper mapper;

	@Override
	protected JpaRepository<TemporaryRentAssessment, Long> getRepository() {
		return temporaryRentAssessmentRepository;
	}
	
	@Override
	public List<TemporaryRentAssessment> searchInLine(String query) {
		return null;
	}
	
	

	@Override
	public TemporaryRentAssessment create(Optional<TemporaryRentAssessmentDTO> optional) {
		if(!optional.isPresent()) return null;
		TemporaryRentAssessment temporaryRentAssessment =new TemporaryRentAssessment();
		BeanUtils.copyProperties(optional.get(), temporaryRentAssessment);
		TemporaryRentFacilities temporaryRentFacilities = optional.get().getTemporaryRentFacilities();
		if(temporaryRentFacilities!=null) {			
			TemporaryRentFacilities facilities = this.temporaryRentFacilitiesService.create(Optional.of(temporaryRentFacilities));
			temporaryRentAssessment.setTemporaryRentFacilities(facilities.getId());
		}
		return this.getRepository().save(temporaryRentAssessment);
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(TemporaryRentAssessment.class);
	}

	public String[] validate(TemporaryRentAssessmentDTO temporaryRentAssessment) {		
		TemporaryRentAssessment entity =new TemporaryRentAssessment();
		BeanUtils.copyProperties(temporaryRentAssessment, entity );
		return this.validate(entity);
	}
	
}
