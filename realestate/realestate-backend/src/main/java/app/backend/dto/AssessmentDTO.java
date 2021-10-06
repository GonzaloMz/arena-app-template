package app.backend.dto;

import app.backend.model.LongRentAssessment;
import app.backend.model.PlaceDescription;
import app.backend.model.PlaceInventory;

public class AssessmentDTO  extends AssessmentFieldsDTO{

	private Long userId;
	
	private Long placeId;
	
	private PlaceDescription placeDescription;
	
	private PlaceInventory placeInventory;
	
	private TemporaryRentAssessmentDTO temporaryRentAssessment;
	
	private LongRentAssessment longRentAssessment;

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

	public PlaceInventory getPlaceInventory() {
		return placeInventory;
	}

	public void setPlaceInventory(PlaceInventory placeInventory) {
		this.placeInventory = placeInventory;
	}

	public TemporaryRentAssessmentDTO getTemporaryRentAssessment() {
		return temporaryRentAssessment;
	}

	public void setTemporaryRentAssessment(TemporaryRentAssessmentDTO temporaryrentAssessment) {
		this.temporaryRentAssessment = temporaryrentAssessment;
	}

	public LongRentAssessment getLongRentAssessment() {
		return longRentAssessment;
	}

	public void setLongRentAssessment(LongRentAssessment longRentAssessment) {
		this.longRentAssessment = longRentAssessment;
	}
	
	
	
}
