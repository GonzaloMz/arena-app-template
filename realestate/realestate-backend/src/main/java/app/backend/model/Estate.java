package app.backend.model;

import javax.persistence.Entity;

import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateStatus;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Key;
import arena.backend.model.extension.Serchable;

@Entity
public class Estate extends AbstractEntity implements AbstractDataTransferObject{

	private Long placeId;
	private Long userId;
	private Double price;
	private EstateOperations operation;
	private EstateType estateType;
	private EstateStatus status;

	@Key(type = Place.class)
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	@Serchable
	@Key(type = User.class)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public EstateOperations getOperation() {
		return operation;
	}

	public void setOperation(EstateOperations operation) {
		this.operation = operation;
	}

	public EstateStatus getStatus() {
		return status;
	}

	public void setStatus(EstateStatus status) {
		this.status = status;
	}

	public EstateType getEstateType() {
		return estateType;
	}

	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}
	
	
	
}
