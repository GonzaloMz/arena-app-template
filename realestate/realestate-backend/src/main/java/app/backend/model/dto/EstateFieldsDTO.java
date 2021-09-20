package app.backend.model.dto;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateStatus;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Required;

@MappedSuperclass
public class EstateFieldsDTO extends AbstractEntity implements AbstractDataTransferObject{

	@Column(name="estate_type")
	private EstateType estateType;
	private EstateOperations operation;
	private EstateStatus status;
	private Long price;
	
	public EstateType getEstateType() {
		return estateType;
	}
	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}
	public EstateStatus getStatus() {
		return status;
	}
	
	@Required
	public void setStatus(EstateStatus status) {
		this.status = status;
	}
	public EstateOperations getOperation() {
		return operation;
	}
	public void setOperation(EstateOperations operation) {
		this.operation = operation;
	}
	public Long getPrice() {
		return price;
	}
	
	@Required
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
