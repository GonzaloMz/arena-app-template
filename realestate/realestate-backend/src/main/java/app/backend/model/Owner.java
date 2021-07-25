package app.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Key;

@Entity
public class Owner extends AbstractEntity implements AbstractDataTransferObject {

	@Column(unique = true)
	private Long user;
	
	private String address;
	
	private Long dni;
	
	private Long cuit;

	@Key(type = User.class)
	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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
