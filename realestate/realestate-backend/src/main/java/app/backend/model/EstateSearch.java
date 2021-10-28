package app.backend.model;

import java.util.Date;

import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;
import app.backend.model.enums.Numeration;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;
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
	
	private Numeration numberOfOcupants;
	
	private Date checkInDate;
	
	private Date checkOutDate;
	

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
	public Numeration getNumberOfOcupants() {
		return numberOfOcupants;
	}
	public void setNumberOfOcupants(Numeration numberOfOcupants) {
		this.numberOfOcupants = numberOfOcupants;
	}
	
	public Date getCheckInDate() {
		return checkInDate;
	}

	@Hidden
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	@Hidden
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
}
