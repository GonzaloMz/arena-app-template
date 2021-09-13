/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.backend.model.Assessment;
import app.backend.model.dto.AssessmentDTO;
import app.backend.service.AssessmentService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/assessment")
public class AssessmentController extends ArenaController<Assessment,AssessmentDTO>{
		
	@Autowired
	private AssessmentService assessmentService;

	@Override
	public ArenaService<Assessment,AssessmentDTO> getService() {
		return this.assessmentService;
	}


}
