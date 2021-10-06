package app.backend.dto;

import java.util.Date;
import java.util.HashMap;

import app.backend.model.LongRentPrice;
import app.backend.model.PlaceInventory;
import app.backend.model.TemporaryRentFacilities;
import app.backend.model.TemporaryRentPrice;

public class EstateDTO extends EstateFieldsDTO{
	
	private OwnerDTO owner;
	
	private Long placeDescription;
	
	private Long placeId;
	
	private PlaceInventory placeInventory;
	
	private TemporaryRentFacilities temporaryRentFacilities;
	
	private TemporaryRentPrice temporaryRentPrice;
	
	private LongRentPrice longRentPrice;
	
	//campos de soporte
	private Double sugestedValue;
	private Date assessmentTimestamp;

	public OwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}

	public Long getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(Long placeDescription) {
		this.placeDescription = placeDescription;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
	
	public HashMap<String, String> getPhotos() {
		HashMap<String, String> filter = null;
		if(this.placeId!=null) {
			filter =new HashMap<>();			
			filter.put("place", this.placeId.toString());
		}
		return filter;
	}

	public Double getSugestedValue() {
		return sugestedValue;
	}

	public void setSugestedValue(Double sugestedValue) {
		this.sugestedValue = sugestedValue;
	}

	public Date getAssessmentTimestamp() {
		return assessmentTimestamp;
	}

	public void setAssessmentTimestamp(Date assessmentTimestamp) {
		this.assessmentTimestamp = assessmentTimestamp;
	}

	public PlaceInventory getPlaceInventory() {
		return placeInventory;
	}

	public void setPlaceInventory(PlaceInventory placeInventory) {
		this.placeInventory = placeInventory;
	}

	public TemporaryRentFacilities getTemporaryRentFacilities() {
		return temporaryRentFacilities;
	}

	public void setTemporaryRentFacilities(TemporaryRentFacilities temporaryRentFacilities) {
		this.temporaryRentFacilities = temporaryRentFacilities;
	}

	public TemporaryRentPrice getTemporaryRentPrice() {
		return temporaryRentPrice;
	}

	public void setTemporaryRentPrice(TemporaryRentPrice temporaryRentPrice) {
		this.temporaryRentPrice = temporaryRentPrice;
	}

	public LongRentPrice getLongRentPrice() {
		return longRentPrice;
	}

	public void setLongRentPrice(LongRentPrice longRentPrice) {
		this.longRentPrice = longRentPrice;
	}

	
	
}
