package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.enums.UserStatus;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;
import arena.backend.model.extension.RegEx;
import arena.backend.model.extension.Required;

@Entity
public class User extends AbstractEntity  implements AbstractDataTransferObject {

	private String name;
	
	private String email;
	
	private String phone;
	
	private UserStatus status;
	
	public String getName() {
		return name;
	}

	@Required
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	@RegEx(expression = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
	@Required
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserStatus getStatus() {
		return status;
	}

	@Hidden
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	
	
}
