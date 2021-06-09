package app.backend.model;

import javax.persistence.Entity;
import arena.backend.model.AbstractEntity;

@Entity
public class Place extends AbstractEntity {

	String formattedAddress;
	
	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedAddress(){
		return this.formattedAddress;
	}
	
}
