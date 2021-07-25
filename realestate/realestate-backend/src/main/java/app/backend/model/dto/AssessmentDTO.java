package app.backend.model.dto;

import app.backend.model.PlaceDescription;

public class AssessmentDTO  extends AssessmentFieldsDTO{

	private Long userId;
	
	private Long placeId;
	
	private PlaceDescription placeDescription;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public PlaceDescription getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(PlaceDescription placeDescription) {
		this.placeDescription = placeDescription;
	}
	
	
	
}
