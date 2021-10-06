package app.backend.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.lang3.ObjectUtils;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;

@Entity
public class TemporaryRentPrice extends AbstractEntity implements AbstractDataTransferObject {
	
	private static final int STAY_DURATION_IN_DAYS = 14;
	
	private static final Double HALF_STAY_MULTIPLICATOR = 0.6;
	
	private static final Double DAILY_MULTIPLICATOR = 0.1;
	
	private Double stayPrice;
	
	private Double halfStayPrice;
	
	private Double dailyPrice;

	public TemporaryRentPrice() {
		super();
	}

	public TemporaryRentPrice(TemporaryRentAssessment assessment) {
		this.stayPrice=assessment.getSuggestedStayPrice();
		this.halfStayPrice= ObjectUtils.firstNonNull(assessment.getSuggestedHalfStayPrice(), this.stayPrice*HALF_STAY_MULTIPLICATOR);
		this.dailyPrice= ObjectUtils.firstNonNull(assessment.getSuggestedDailyPrice(), this.stayPrice*DAILY_MULTIPLICATOR);
	}

	public Double getStayPrice() {
		return stayPrice;
	}

	public void setStayPrice(Double stayPrice) {
		this.stayPrice = stayPrice;
	}

	public Double getDailyPrice() {
		return ObjectUtils.firstNonNull(this.dailyPrice, this.stayPrice*DAILY_MULTIPLICATOR);
	}

	public void setDailyPrice(Double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	
	@Transient
	public Double getStayDailyPrice() {
		return this.stayPrice / STAY_DURATION_IN_DAYS;
	}
	
	@Transient
	public Double getHalfStayDailyPrice() {
		Double basePrice = ObjectUtils.firstNonNull(this.halfStayPrice, this.stayPrice*HALF_STAY_MULTIPLICATOR);
		return basePrice / (STAY_DURATION_IN_DAYS/2);
	}

	public Double getHalfStayPrice() {
		return halfStayPrice;
	}

	public void setHalfStayPrice(Double halfStayPrice) {
		this.halfStayPrice = halfStayPrice;
	}

	
}
