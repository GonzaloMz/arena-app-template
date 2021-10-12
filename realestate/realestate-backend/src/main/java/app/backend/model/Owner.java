package app.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import app.backend.dto.OwnerFieldsDTO;
import arena.backend.model.extension.Key;
import arena.backend.model.extension.Required;

@Entity
public class Owner extends OwnerFieldsDTO {

	@Column(unique = true)
	private Long user;
	
	private Long address;
	
	private Long dni;
	
	private Long cuit;

	@Key(type = User.class, allowInLineCreate = true)
	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	@Key(type=Place.class, allowInLineCreate = true)
	public Long getAddress() {
		return address;
	}

	public void setAddress(Long address) {
		this.address = address;
	}

	public Long getDni() {
		return dni;
	}

	@Required
	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Long getCuit() {
		return cuit;
	}

	@Required
	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	
	
	
}
