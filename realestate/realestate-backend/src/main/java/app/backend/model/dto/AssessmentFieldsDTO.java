package app.backend.model.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;

@MappedSuperclass
public class AssessmentFieldsDTO extends AbstractEntity implements AbstractDataTransferObject{

	private Double sugestedValue;
	private EstateOperations operation;
	private EstateType estateType;
	@CreationTimestamp
	private Date timestamp;
	
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
	public EstateType getEstateType() {
		return estateType;
	}
	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	@Hidden
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
