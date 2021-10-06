package app.backend.model;

import javax.persistence.Entity;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Required;

@Entity
public class LongRentAssessment extends AbstractEntity implements AbstractDataTransferObject {

	private Double montlySuggestedPrice;
	
	private Double suggestedPriceAdjustment;

	public Double getMontlySuggestedPrice() {
		return montlySuggestedPrice;
	}

	@Required
	public void setMontlySuggestedPrice(Double montlySuggestedPrice) {
		this.montlySuggestedPrice = montlySuggestedPrice;
	}

	public Double getSuggestedPriceAdjustment() {
		return suggestedPriceAdjustment;
	}

	public void setSuggestedPriceAdjustment(Double suggestedPriceAdjustment) {
		this.suggestedPriceAdjustment = suggestedPriceAdjustment;
	}
	
	
}
