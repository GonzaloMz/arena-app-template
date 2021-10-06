package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.enums.Numeration;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

@Entity
public class PlaceInventory extends AbstractEntity implements AbstractDataTransferObject {

	private Boolean refrigerator;
	private Boolean stove;
	private Boolean oven;
	private Boolean airExtractor;
	private Boolean hotWaterTank;
	private Boolean waterHeater;
	private Numeration heaters;
	private Numeration airConditioners;
	private Numeration radiators;
	private Numeration fans;
	
	public Boolean getRefrigerator() {
		return refrigerator;
	}
	public void setRefrigerator(Boolean refrigerator) {
		this.refrigerator = refrigerator;
	}
	public Boolean getStove() {
		return stove;
	}
	public void setStove(Boolean stove) {
		this.stove = stove;
	}
	public Boolean getAirExtractor() {
		return airExtractor;
	}
	public void setAirExtractor(Boolean airExtractor) {
		this.airExtractor = airExtractor;
	}
	public Boolean getHotWaterTank() {
		return hotWaterTank;
	}
	public void setHotWaterTank(Boolean hotWaterTank) {
		this.hotWaterTank = hotWaterTank;
	}
	public Boolean getWaterHeater() {
		return waterHeater;
	}
	public void setWaterHeater(Boolean waterHeater) {
		this.waterHeater = waterHeater;
	}
	public Numeration getHeaters() {
		return heaters;
	}
	public void setHeaters(Numeration heaters) {
		this.heaters = heaters;
	}
	public Numeration getAirConditioners() {
		return airConditioners;
	}
	public void setAirConditioners(Numeration airConditioners) {
		this.airConditioners = airConditioners;
	}
	public Boolean getOven() {
		return oven;
	}
	public void setOven(Boolean oven) {
		this.oven = oven;
	}
	public Numeration getRadiators() {
		return radiators;
	}
	public void setRadiators(Numeration radiators) {
		this.radiators = radiators;
	}
	public Numeration getFans() {
		return fans;
	}
	public void setFans(Numeration fans) {
		this.fans = fans;
	}
	
	
}
