/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.dto.TemporaryRentAssessmentDTO;
import app.backend.model.TemporaryRentAssessment;
import app.backend.service.TemporaryRentAssessmentService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/temporaryRentAssessment")
public class TemporaryRentAssessmentController extends ArenaController<TemporaryRentAssessment,TemporaryRentAssessmentDTO>{
		
	@Autowired
	private TemporaryRentAssessmentService temporaryRentAssessmentService;

	@Override
	public ArenaService<TemporaryRentAssessment,TemporaryRentAssessmentDTO> getService() {
		return this.temporaryRentAssessmentService;
	}

}
