/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Appointment;
import app.backend.model.Assessment;
import app.backend.repository.AssessmentRepository;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class AssessmentService extends ArenaService<Assessment,Assessment>{
	
	@Autowired
	AssessmentRepository assessmentRepository;
	
	@Autowired
	AppointmentService appointmentService;

	@Override
	protected JpaRepository<Assessment, Long> getRepository() {
		return assessmentRepository;
	}

	@Override
	public Assessment create(Optional<Assessment> ent) {
		
		Assessment entity = new Assessment();
		if(ent.isPresent()) {
			Long appointment = ent.get().getAppointment();
			if (appointment!=null) {
				Appointment app = appointmentService.get(appointment);
				if(app!=null) {
					BeanUtils.copyProperties(app, entity);
				}
			}
		}
		return this.getRepository().save(entity);
	}

	@Override
	public List<Assessment> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
