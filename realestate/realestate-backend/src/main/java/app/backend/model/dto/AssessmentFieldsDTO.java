package app.backend.model.dto;

import javax.persistence.MappedSuperclass;

import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

@MappedSuperclass
public class AssessmentFieldsDTO extends AbstractEntity implements AbstractDataTransferObject{

	private Double sugestedValue;
	private EstateOperations operation;
	private EstateType estateType;
	
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
	
	
}
