package app.backend.model.dto;

import app.backend.model.Place;

public class OwnerDTO extends OwnerFieldsDTO{

	private Long user;
	
	private Place address;

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Place getAddress() {
		return address;
	}

	public void setAddress(Place address) {
		this.address = address;
	}
	
	
}
