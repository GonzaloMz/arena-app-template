/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.Assessment;
import app.backend.service.AssessmentService;
import arena.backend.controller.ArenaController;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/assessment")
public class AssessmentController extends ArenaController<Assessment,Assessment>{
		
	@Autowired
	private AssessmentService assessmentService;

	@Override
	public ArenaService<Assessment,Assessment> getService() {
		return this.assessmentService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Assessment.class);
	}
	
}
