package app.backend.dto;

import app.backend.model.Place;
import app.backend.model.User;

public class OwnerWithUserDTO extends OwnerDTO{
	
	private User user;

	public User getUserDTO() {
		return user;
	}

	public void setUser(User user) {
		this.user = (User) user;
	}

	
	
}
