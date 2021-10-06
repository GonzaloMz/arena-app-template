package app.backend.dto;

import app.backend.model.TemporaryRentFacilities;

public class TemporaryRentAssessmentDTO extends TemporaryRentAssessmentFieldsDTO{

	private TemporaryRentFacilities temporaryRentFacilities;

	public TemporaryRentFacilities getTemporaryRentFacilities() {
		return temporaryRentFacilities;
	}

	public void setTemporaryRentFacilities(TemporaryRentFacilities temporaryRentFacilities) {
		this.temporaryRentFacilities = temporaryRentFacilities;
	}
	
	
	
}
