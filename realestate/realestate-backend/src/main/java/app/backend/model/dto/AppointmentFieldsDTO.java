package app.backend.model.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import app.backend.model.enums.AppointmentStatus;
import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.Hidden;

@MappedSuperclass
public class AppointmentFieldsDTO extends AbstractEntity{

	private Date appointmentDate;
	
	private  EstateOperations operation;
	
	private AppointmentStatus status;
	
	private EstateType estateType;

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public EstateOperations getOperation() {
		return operation;
	}

	public void setOperation(EstateOperations operation) {
		this.operation = operation;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	@Hidden
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public EstateType getEstateType() {
		return estateType;
	}

	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}
	
	
}
