package app.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Key;

//@Entity
//@Table(name="place_description")
//@SecondaryTable(name="estate", pkJoinColumns = @PrimaryKeyJoinColumn(name = "place_description"))
public class EstateSearch extends AbstractEntity implements AbstractDataTransferObject {

//	@Column(name="estate_type", table="estate")
	private EstateType estateType;
	
//	@Column(name="operation", table="estate")
	private EstateOperations operation;
	
//	@Column(name="price", table="estate")
	private Double price;
	
//	@Column(name="id", table="estate")
	private Long estate;
	
//	@Column(name="environments")
	private Integer environments;

	public EstateSearch() {
		
	}
	public EstateSearch(Estate e) {
		this.id=e.getId();
		this.estate=e.getId();
	}

	public EstateType getEstateType() {
		return estateType;
	}

	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}

	public EstateOperations getOperation() {
		return operation;
	}

	public void setOperation(EstateOperations operation) {
		this.operation = operation;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Key(type=Estate.class)
	public Long getEstate() {
		return estate;
	}

	public void setEstate(Long estate) {
		this.estate = estate;
	}

	public Integer getEnvironments() {
		return environments;
	}

	public void setEnvironments(Integer environments) {
		this.environments = environments;
	}
	
	
}
