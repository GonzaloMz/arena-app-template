/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.Appointment;
import app.backend.model.dto.AppointmentDTO;
import app.backend.service.AppointmentService;
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
@RequestMapping(value = "/appointment")
public class AppointmentController extends ArenaController<Appointment, AppointmentDTO>{
		
	@Autowired
	private AppointmentService appointmentService;

	@Override
	public ArenaService<Appointment, AppointmentDTO> getService() {
		return this.appointmentService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Appointment.class);
	}
	
}
