package app.backend.model;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;

@Entity
public class Photo extends AbstractEntity implements AbstractDataTransferObject {
	
	private Long place;

	@Type(type = "org.hibernate.type.TextType")
	private String view;

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Long getPlace() {
		return place;
	}

	@Hidden
	public void setPlace(Long place) {
		this.place = place;
	}

	
	
	
}
