/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.LongRentAssessment;
import app.backend.repository.LongRentAssessmentRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class LongRentAssessmentService extends ArenaService<LongRentAssessment,LongRentAssessment>{
	
	@Autowired
	LongRentAssessmentRepository longRentAssessmentRepository;

	@Override
	protected JpaRepository<LongRentAssessment, Long> getRepository() {
		return longRentAssessmentRepository;
	}
	
	@Override
	public List<LongRentAssessment> searchInLine(String query) {
		return null;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(LongRentAssessment.class);
	}
	
}
