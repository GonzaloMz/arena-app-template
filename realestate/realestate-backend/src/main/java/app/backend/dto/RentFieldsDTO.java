package app.backend.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;
import arena.backend.model.extension.Required;

@MappedSuperclass
public class RentFieldsDTO extends AbstractEntity implements AbstractDataTransferObject {

	protected static final Double MINIMUM_PARTIAL_PAYMENT_PERCENTAGE = 0.4;

	protected static final Double COMMISSION_PERCENTAGE = 0.1;

	private Date checkInDate;
	
	private Date checkOutDate;
	
	private Double stayTotal;
	
	private Double commission;
	
	private Boolean active;
	
	private Boolean partialPaymentReceived;
	
	private Double discount;
	
	private Boolean keyDelivered;
	
	private Double dischargedTotal;
	
	private Boolean discharged;
	
	private Boolean inventaryChecked;
	
	private Boolean securityDepositReturned;
	
	@CreationTimestamp
	private Date timestamp;

	public Date getCheckInDate() {
		return checkInDate;
	}

	@Required
	@Hidden
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	@Required
	@Hidden
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	@Hidden
	public Double getTotal() {
		if(stayTotal==null) return null;
		return stayTotal + getCommission() - getDiscount();
	}
	
	public Double getDiscount() {
		if(discount==null) return 0d;
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Hidden
	public Double getMinimumPartialPayment() {
		if(this.stayTotal==null) return null;
		return this.stayTotal * MINIMUM_PARTIAL_PAYMENT_PERCENTAGE;
	}

	public Double getCommission() {
		if(this.stayTotal==null) return null;
		if (commission==null)
			return this.stayTotal * COMMISSION_PERCENTAGE;
		return commission;
	}

	@Hidden
	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Boolean getActive() {
		return active;
	}

	@Hidden
	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getPartialPaymentReceived() {
		return partialPaymentReceived;
	}

	@Hidden
	public void setPartialPaymentReceived(Boolean partialPaymentReceived) {
		this.partialPaymentReceived = partialPaymentReceived;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Hidden
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Hidden
	public Double getStayTotal() {
		return stayTotal;
	}

	public void setStayTotal(Double stayTotal) {
		this.stayTotal = stayTotal;
	}

	public Boolean getKeyDelivered() {
		return keyDelivered;
	}

	public void setKeyDelivered(Boolean keyDelivered) {
		this.keyDelivered = keyDelivered;
	}
	
	public Boolean getInventaryChecked() {
		return inventaryChecked;
	}

	public void setInventaryChecked(Boolean inventaryChecked) {
		this.inventaryChecked = inventaryChecked;
	}

	public Boolean getSecurityDepositReturned() {
		return securityDepositReturned;
	}

	public void setSecurityDepositReturned(Boolean securityDepositReturned) {
		this.securityDepositReturned = securityDepositReturned;
	}

	public Double getDischargedTotal() {
		return dischargedTotal;
	}

	public void setDischargedTotal(Double dischargedTotal) {
		if(dischargedTotal==null) return;
		if (this.dischargedTotal==null) this.dischargedTotal=0d;
		this.dischargedTotal += dischargedTotal;
		if(this.stayTotal==null) return;
		if(this.dischargedTotal>=getMinimumPartialPayment())
			this.partialPaymentReceived=true;
		if(this.dischargedTotal>=getTotal())
			this.discharged=true;
	}

	public Boolean getDischarged() {
		return discharged;
	}

	public void setDischarged(Boolean discharged) {
		this.discharged = discharged;
	}
	
	
	
}
