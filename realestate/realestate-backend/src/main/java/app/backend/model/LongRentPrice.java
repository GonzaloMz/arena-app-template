package app.backend.model;

import javax.persistence.Entity;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

@Entity
public class LongRentPrice extends AbstractEntity implements AbstractDataTransferObject {

	private Double montlyPrice;
	
	private Double priceAdjustment;

	public Double getMontlyPrice() {
		return montlyPrice;
	}

	public void setMontlyPrice(Double montlyPrice) {
		this.montlyPrice = montlyPrice;
	}

	public Double getPriceAdjustment() {
		return priceAdjustment;
	}

	public void setPriceAdjustment(Double priceAdjustment) {
		this.priceAdjustment = priceAdjustment;
	}


	
}
