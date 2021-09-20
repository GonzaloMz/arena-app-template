package app.backend.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import app.backend.model.dto.EstateFieldsDTO;
import arena.backend.model.extension.Hidden;
import arena.backend.model.extension.Key;
import arena.backend.model.extension.TemplateField;

@Entity
public class Estate extends EstateFieldsDTO{

	private Long placeId;
	
	private Long owner;

	@Column(name="place_description")
	private Long placeDescription;
	
	@Key(type = Place.class)
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	@Key(type=Owner.class, allowInLineCreate = true)
	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	@Key(type = PlaceDescription.class)
	public Long getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(Long placeDescription) {
		this.placeDescription = placeDescription;
	}

	@Key(type=Photo.class, allowInLineCreate = true)
	@TemplateField
	public HashMap<String, String> getPhotos() {
		HashMap<String, String> filter = null;
		if(this.placeId!=null) {
			filter =new HashMap<>();			
			filter.put("place", this.placeId.toString());
		}
		return filter;
	}
	
	@Hidden
	@Transient
	public String getCurrencySymbol() {
		return this.getOperation() != null ? this.getOperation().getCurrency() : "$";	
	}
}
