package app.backend.model.enums;

public enum EstateOperations {
	
	SALE("USD"), RENT("ARS");

	private String currency;

	EstateOperations(String string) {
		this.currency=string;
	}

	public String getCurrency() {
		return currency;
	}

	
}
