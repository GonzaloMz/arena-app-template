package app.backend.model;

import javax.persistence.Entity;

import app.backend.dto.TemporaryRentAssessmentFieldsDTO;
import arena.backend.model.extension.Key;

@Entity
public class TemporaryRentAssessment extends TemporaryRentAssessmentFieldsDTO {

	private Long temporaryRentFacilities;

	@Key(type=TemporaryRentFacilities.class, allowInLineCreate = true)
	public Long getTemporaryRentFacilities() {
		return temporaryRentFacilities;
	}

	public void setTemporaryRentFacilities(Long temporaryRentFacilities) {
		this.temporaryRentFacilities = temporaryRentFacilities;
	}
	
	
	
}
