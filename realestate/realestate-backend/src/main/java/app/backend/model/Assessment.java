package app.backend.model;

import javax.persistence.Entity;

import app.backend.dto.AssessmentFieldsDTO;
import arena.backend.model.extension.Key;
import arena.backend.model.extension.Required;

@Entity
public class Assessment extends AssessmentFieldsDTO{

	private Long placeId;
	private Long userId;
	private Long placeDescription;
	private Long temporaryRentAssessment;
	private Long longRentAssessment;
	private Long placeInventory;


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

	@Key(type=PlaceDescription.class, allowInLineCreate = true)
	public Long getPlaceDescription() {
		return placeDescription;
	}

	@Required
	public void setPlaceDescription(Long placeDescription) {
		this.placeDescription = placeDescription;
	}
	
	@Key(type=TemporaryRentAssessment.class, allowInLineCreate = true)
	public Long getTemporaryRentAssessment() {
		return temporaryRentAssessment;
	}

	public void setTemporaryRentAssessment(Long temporaryrentAssessment) {
		this.temporaryRentAssessment = temporaryrentAssessment;
	}

	@Key(type=LongRentAssessment.class, allowInLineCreate = true)
	public Long getLongRentAssessment() {
		return longRentAssessment;
	}

	public void setLongRentAssessment(Long longRentAssessment) {
		this.longRentAssessment = longRentAssessment;
	}

	@Key(type=PlaceInventory.class, allowInLineCreate = true)
	public Long getPlaceInventory() {
		return placeInventory;
	}

	public void setPlaceInventory(Long placeInventory) {
		this.placeInventory = placeInventory;
	}


	
	
}

