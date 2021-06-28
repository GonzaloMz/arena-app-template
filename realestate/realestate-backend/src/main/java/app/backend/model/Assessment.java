package app.backend.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import app.backend.model.enums.EstateOperations;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Key;
import arena.backend.model.extension.TemplateField;

@Entity
public class Assessment extends AbstractEntity implements AbstractDataTransferObject {

	private Long placeId;
	private Long userId;
	private Double sugestedValue;
	private EstateOperations operation;
	@Transient
	private Long appointment;
	

	@Key(type = Place.class)
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	@Key(type = User.class)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getSugestedValue() {
		return sugestedValue;
	}

	public void setSugestedValue(Double sugestedValue) {
		this.sugestedValue = sugestedValue;
	}

	public EstateOperations getOperation() {
		return operation;
	}

	public void setOperation(EstateOperations operation) {
		this.operation = operation;
	}

	@TemplateField
	public Long getAppointment() {
		return appointment;
	}

	public void setAppointment(Long appointment) {
		this.appointment = appointment;
	}
	
	
	
	
}

