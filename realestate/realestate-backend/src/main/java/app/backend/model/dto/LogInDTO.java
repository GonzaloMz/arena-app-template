package app.backend.model.dto;

import app.backend.model.Administrator;

public class LogInDTO extends LogInFieldsDTO {

	private Administrator administrator;

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	
}
