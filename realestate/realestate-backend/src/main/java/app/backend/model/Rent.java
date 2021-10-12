package app.backend.model;

import javax.persistence.Entity;

import app.backend.dto.RentFieldsDTO;
import arena.backend.model.extension.Key;

@Entity
public class Rent extends RentFieldsDTO {

	
	private Long tenant;
	
	private Long estate;
	
	@Key(type=Owner.class, allowInLineSearch = true, allowInLineCreate = true)
	public Long getTenant() {
		return tenant;
	}

	public void setTenant(Long owner) {
		this.tenant = owner;
	}

	@Key(type=Estate.class)
	public Long getEstate() {
		return estate;
	}

	public void setEstate(Long estate) {
		this.estate = estate;
	}
	
}
