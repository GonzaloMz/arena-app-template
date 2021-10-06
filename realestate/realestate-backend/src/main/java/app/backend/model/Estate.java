package app.backend.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import app.backend.dto.EstateFieldsDTO;
import arena.backend.model.extension.Hidden;
import arena.backend.model.extension.Key;
import arena.backend.model.extension.TemplateField;

@Entity
public class Estate extends EstateFieldsDTO{

	private Long placeId;
	
	private Long owner;
	
	private Long placeInventory;
	
	private Long temporaryRentFacilities;
	
	private Long temporaryRentPrice;
	
	private Long longRentPrice;

	private Double salePrice;
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
	
	@Key(type=PlaceInventory.class, allowInLineCreate = true)
	public Long getPlaceInventory() {
		return placeInventory;
	}

	public void setPlaceInventory(Long placeInventory) {
		this.placeInventory = placeInventory;
	}

	@Key(type=TemporaryRentFacilities.class, allowInLineCreate = true)
	public Long getTemporaryRentFacilities() {
		return temporaryRentFacilities;
	}

	public void setTemporaryRentFacilities(Long temporaryRentFacilities) {
		this.temporaryRentFacilities = temporaryRentFacilities;
	}

	@Key(type=TemporaryRentPrice.class, allowInLineCreate = true)
	public Long getTemporaryRentPrice() {
		return temporaryRentPrice;
	}

	public void setTemporaryRentPrice(Long temporaryRentPrice) {
		this.temporaryRentPrice = temporaryRentPrice;
	}

	@Key(type=LongRentPrice.class, allowInLineCreate = true)
	public Long getLongRentPrice() {
		return longRentPrice;
	}

	public void setLongRentPrice(Long longRentPrice) {
		this.longRentPrice = longRentPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	
}
