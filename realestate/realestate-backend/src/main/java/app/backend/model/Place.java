package app.backend.model;

import javax.persistence.Entity;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;
import arena.backend.model.extension.Required;

@Entity
public class Place extends AbstractEntity  implements AbstractDataTransferObject{

	String formattedAddress;
	
	private Integer floor;
	
	private String unit;
	
	private String locality;
	
	private Double latitude;
	
	private Double longitude;
	
	@Required
	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedAddress(){
		return this.formattedAddress;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLocality() {
		return locality;
	}

	@Required
	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Double getLatitude() {
		return latitude;
	}

	@Hidden
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	@Hidden
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
