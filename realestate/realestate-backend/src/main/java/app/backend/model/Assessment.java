package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.dto.AssessmentFieldsDTO;
import arena.backend.model.extension.Key;

@Entity
public class Assessment extends AssessmentFieldsDTO{

	private Long placeId;
	private Long userId;


	@Key(type = Place.class)
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
	
	@Key(type = User.class)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}

