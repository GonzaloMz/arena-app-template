package app.backend.model.dto;

import java.util.Date;
import java.util.HashMap;

public class EstateDTO extends EstateFieldsDTO{
	
	private OwnerDTO owner;
	
	private Long placeDescription;
	
	private Long placeId;
	
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


}
