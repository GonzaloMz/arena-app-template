package app.backend.dto;

import javax.persistence.MappedSuperclass;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Required;

@MappedSuperclass
public class TemporaryRentAssessmentFieldsDTO extends AbstractEntity implements AbstractDataTransferObject {

	private Double suggestedStayPrice;

	private Double suggestedHalfStayPrice;

	private Double suggestedDailyPrice;

	public Double getSuggestedStayPrice() {
		return suggestedStayPrice;
	}

	@Required
	public void setSuggestedStayPrice(Double suggestedStayPrice) {
		this.suggestedStayPrice = suggestedStayPrice;
	}

	public Double getSuggestedHalfStayPrice() {
		return suggestedHalfStayPrice;
	}

	public void setSuggestedHalfStayPrice(Double suggestedHalfStayPrice) {
		this.suggestedHalfStayPrice = suggestedHalfStayPrice;
	}

	public Double getSuggestedDailyPrice() {
		return suggestedDailyPrice;
	}

	public void setSuggestedDailyPrice(Double suggestedDailyPrice) {
		this.suggestedDailyPrice = suggestedDailyPrice;
	}
	
	
	
}
