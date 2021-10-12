package app.backend.dto;

import javax.persistence.Entity;

@Entity
public class RentDTO extends RentFieldsDTO {

	private OwnerWithUserDTO tenant;
	
	private Long estate;

	public OwnerWithUserDTO getTenant() {
		return tenant;
	}

	public void setTenant(OwnerWithUserDTO tenant) {
		this.tenant = tenant;
	}

	public Long getEstate() {
		return estate;
	}

	public void setEstate(Long estate) {
		this.estate = estate;
	}
	
	
	
}
