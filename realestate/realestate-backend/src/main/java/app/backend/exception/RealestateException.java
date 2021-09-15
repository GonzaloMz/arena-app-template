package app.backend.exception;

import java.util.Arrays;

public class RealestateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String[] errors;

	public RealestateException(String[] array) {
		this.errors=array;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return Arrays.toString(errors);
	}
	
	
}
