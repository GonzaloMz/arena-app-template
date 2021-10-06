/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.LongRentAssessment;
import app.backend.service.LongRentAssessmentService;
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
@RequestMapping(value = "/longRentAssessment")
public class LongRentAssessmentController extends ArenaController<LongRentAssessment,LongRentAssessment>{
		
	@Autowired
	private LongRentAssessmentService longRentAssessmentService;

	@Override
	public ArenaService<LongRentAssessment,LongRentAssessment> getService() {
		return this.longRentAssessmentService;
	}

}
