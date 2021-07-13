package app.backend.model.dto;

import app.backend.model.Place;
import app.backend.model.User;

public class AssessmentDTO  extends AssessmentFieldsDTO{

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
