package app.backend.model.dto;

import javax.persistence.MappedSuperclass;

import app.backend.model.Place;
import app.backend.model.User;
import arena.backend.model.extension.AbstractDataTransferObject;

@MappedSuperclass
public class AppointmentDTO extends AppointmentFieldsDTO implements AbstractDataTransferObject {


	private User userId;
	
	private Place placeId;

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Place getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Place placeId) {
		this.placeId = placeId;
	}

}
