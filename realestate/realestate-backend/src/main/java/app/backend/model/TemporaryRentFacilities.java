package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.enums.Numeration;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

@Entity
public class TemporaryRentFacilities extends AbstractEntity implements AbstractDataTransferObject {

	private Boolean tv;
	private Boolean wifi;
	private Boolean microwave;
	private Boolean electricKettle;
	private Boolean petFriendly;
	private Boolean soundSystem;
	private Numeration numberOfOcupants;
	
	public Boolean getTv() {
		return tv;
	}
	public void setTv(Boolean tv) {
		this.tv = tv;
	}
	public Boolean getWifi() {
		return wifi;
	}
	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}
	public Boolean getMicrowave() {
		return microwave;
	}
	public void setMicrowave(Boolean microwave) {
		this.microwave = microwave;
	}
	public Boolean getElectricKettle() {
		return electricKettle;
	}
	public void setElectricKettle(Boolean electricKettle) {
		this.electricKettle = electricKettle;
	}

	public Boolean getPetFriendly() {
		return petFriendly;
	}
	public void setPetFriendly(Boolean petFriendly) {
		this.petFriendly = petFriendly;
	}
	public Boolean getSoundSystem() {
		return soundSystem;
	}
	public void setSoundSystem(Boolean soundSystem) {
		this.soundSystem = soundSystem;
	}
	public Numeration getNumberOfOcupants() {
		return numberOfOcupants;
	}
	public void setNumberOfOcupants(Numeration numberOfOcupants) {
		this.numberOfOcupants = numberOfOcupants;
	}
	
	
}
