package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.dto.LogInFieldsDTO;
import arena.backend.model.extension.Key;

@Entity
public class LogIn extends LogInFieldsDTO{

	private Long administrator;
	
	@Key(type = Administrator.class, allowInLineCreate = true)
	public Long getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Long administrator) {
		this.administrator = administrator;
	}

	
}
