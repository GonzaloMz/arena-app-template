/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Appointment;
import app.backend.model.Assessment;
import app.backend.model.dto.AssessmentFieldsDTO;
import app.backend.repository.AssessmentRepository;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Map;
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
//		Assessment assessment =  new Assessment();
//		BeanUtils.copyProperties(ent.get(), assessment);
//		BeanUtils.copyProperties((AssessmentFieldsDTO) ent.get(), assessment);
		return this.getRepository().save(ent.get());
	}

	@Override
	public List<Assessment> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assessment buildTemplate(Map<String, String> parameters) {
		try {
			Assessment assessment = new Assessment();
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
	
}
