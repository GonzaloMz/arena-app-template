package app.backend.model.dto;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

public class OwnerFieldsDTO extends AbstractEntity implements AbstractDataTransferObject {

	private Long dni;
	
	private Long cuit;

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	
	
}
