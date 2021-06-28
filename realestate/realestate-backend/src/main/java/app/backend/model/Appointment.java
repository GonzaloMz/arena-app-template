package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.dto.AppointmentFieldsDTO;
import arena.backend.model.extension.Key;

@Entity
public class Appointment extends AppointmentFieldsDTO{

	private Long placeId;
	
	private Long userId;

	@Key(type = Place.class, allowInLineCreate = true)
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	@Key(type = User.class, allowInLineCreate = true, allowInLineSearch = true)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
