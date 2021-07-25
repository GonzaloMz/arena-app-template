package app.backend.model.dto;

import java.util.HashMap;

import app.backend.model.Owner;

public class EstateDTO extends EstateFieldsDTO{
	
	private Owner owner;
	
	private Long placeDescription;
	
	private Long placeId;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
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

	
}
