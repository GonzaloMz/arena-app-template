/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.exception.RealestateException;
import app.backend.model.Appointment;
import app.backend.model.Place;
import app.backend.model.User;
import app.backend.model.dto.AppointmentDTO;
import app.backend.model.dto.AppointmentFieldsDTO;
import app.backend.model.enums.AppointmentStatus;
import app.backend.model.enums.EstateType;
import app.backend.repository.AppointmentRepository;
import app.backend.utils.ErrorBuffer;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class AppointmentService extends ArenaService<Appointment, AppointmentDTO>{
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	PlaceService placeService;
	
	@Autowired
	UserService userService;
	
	@Override
	protected JpaRepository<Appointment, Long> getRepository() {
		return appointmentRepository;
	}

	@Override
	@Transactional
	public Appointment create(Optional<AppointmentDTO> appointment) {
		Appointment app = new Appointment();
		BeanUtils.copyProperties((AppointmentFieldsDTO) appointment.get(), app);
		ErrorBuffer errors = new ErrorBuffer();
		errors.append(this.validate(app));
		Place placeId = appointment.get().getPlaceId();
		errors.append("place",placeService.validate(placeId));
		errors.append("user",userService.validate(appointment.get().getUserId()));
		if(EstateType.DEPARTAMENTO.equals(app.getEstateType()) && StringUtils.isBlank(placeId.getUnit()))
			errors.append("appointment.place.unit.required");
		if(errors.getErrors().length>0) {
			throw new RealestateException(errors.getErrors());
		}
		Place p = placeService.create(Optional.ofNullable(appointment.get().getPlaceId()));
		User u = userService.create(Optional.ofNullable(appointment.get().getUserId()));
		app.setPlaceId(p.getId());
		app.setUserId(u.getId());
		app.setStatus(app.getAppointmentDate()==null ?
							AppointmentStatus.WITHOUT_DATE:
								AppointmentStatus.ACTIVE);
		
		return this.appointmentRepository.save(app);
	}

	@Override
	public List<Appointment> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment update(Appointment arg0) {
		// TODO Auto-generated method stub
		Appointment updated = super.update(arg0);
		if(updated.getStatus()==null && updated.getUserId()!=null && updated.getPlaceId()!=null) {
			updated.setStatus(
					updated.getAppointmentDate()==null ?
							AppointmentStatus.WITHOUT_DATE:
								AppointmentStatus.ACTIVE);
		}
		return this.getRepository().save(updated);
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Appointment.class);
	}
	
}
