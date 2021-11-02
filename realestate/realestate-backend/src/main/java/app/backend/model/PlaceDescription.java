package app.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import app.backend.model.enums.Numeration;
import app.backend.model.enums.SquareMeterRange;
import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Required;

@Entity
@Table(name="place_description")
public class PlaceDescription extends AbstractEntity  implements AbstractDataTransferObject  {

	private SquareMeterRange squareMeterCovered;
	
	private SquareMeterRange squareMeterTotal;
	
	private Numeration environments;
	
	private Numeration toilets;
	
	private Boolean toilete;
	
	private Numeration carPlaces;
	
	private Boolean coveredGarage;
	
	private Boolean laundry;
	
	private Boolean electricity;
	
	private Boolean gas;
	
	private Boolean waterNetworkConnection;
	
	private Boolean sewerConnection;
	
	private String extraNotes;

	public SquareMeterRange getSquareMeterCovered() {
		return squareMeterCovered;
	}

	public void setSquareMeterCovered(SquareMeterRange squareMeterCovered) {
		this.squareMeterCovered = squareMeterCovered;
	}

	public SquareMeterRange getSquareMeterTotal() {
		return squareMeterTotal;
	}

	@Required
	public void setSquareMeterTotal(SquareMeterRange squareMeterTotal) {
		this.squareMeterTotal = squareMeterTotal;
	}

	public Numeration getEnvironments() {
		return environments;
	}

	public void setEnvironments(Numeration environments) {
		this.environments = environments;
	}

	public Numeration getToilets() {
		return toilets;
	}

	public void setToilets(Numeration toilets) {
		this.toilets = toilets;
	}

	public Boolean getToilete() {
		return toilete;
	}

	public void setToilete(Boolean toilete) {
		this.toilete = toilete;
	}

	public Numeration getCarPlaces() {
		return carPlaces;
	}

	public void setCarPlaces(Numeration carPlaces) {
		this.carPlaces = carPlaces;
	}

	public Boolean getCoveredGarage() {
		return coveredGarage;
	}

	public void setCoveredGarage(Boolean coveredGarage) {
		this.coveredGarage = coveredGarage;
	}

	public Boolean getLaundry() {
		return laundry;
	}

	public void setLaundry(Boolean laundry) {
		this.laundry = laundry;
	}

	public Boolean getElectricity() {
		return electricity;
	}

	public void setElectricity(Boolean electricity) {
		this.electricity = electricity;
	}

	public Boolean getGas() {
		return gas;
	}

	public void setGas(Boolean gas) {
		this.gas = gas;
	}

	public Boolean getWaterNetworkConnection() {
		return waterNetworkConnection;
	}

	public void setWaterNetworkConnection(Boolean waterNetworkConnection) {
		this.waterNetworkConnection = waterNetworkConnection;
	}

	public Boolean getSewerConnection() {
		return sewerConnection;
	}

	public void setSewerConnection(Boolean sewerConnection) {
		this.sewerConnection = sewerConnection;
	}

	public String getExtraNotes() {
		return extraNotes;
	}

	public void setExtraNotes(String extraNotes) {
		this.extraNotes = extraNotes;
	}
	
	
}
