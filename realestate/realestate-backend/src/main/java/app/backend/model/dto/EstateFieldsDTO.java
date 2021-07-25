package app.backend.model.dto;

import javax.persistence.MappedSuperclass;

import app.backend.model.enums.EstateStatus;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

@MappedSuperclass
public class EstateFieldsDTO extends AbstractEntity implements AbstractDataTransferObject{

	private EstateType estateType;
	private EstateStatus status;
	public EstateType getEstateType() {
		return estateType;
	}
	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}
	public EstateStatus getStatus() {
		return status;
	}
	public void setStatus(EstateStatus status) {
		this.status = status;
	}
	
	
}
