package app.backend.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

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
	private Boolean estateCreated;
	private Double saleSuggestedValue;
	
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

	public Boolean getEstateCreated() {
		return estateCreated;
	}

	@Hidden
	public void setEstateCreated(Boolean estateCreated) {
		this.estateCreated = estateCreated;
	}

	public Double getSaleSuggestedValue() {
		return saleSuggestedValue;
	}

	public void setSaleSuggestedValue(Double saleSuggestedValue) {
		this.saleSuggestedValue = saleSuggestedValue;
	}
	
	@Hidden
	@Transient
	public String getCurrencySymbol() {
		return this.getOperation() != null ? this.getOperation().getCurrency() : "$";	
	}
	
}
