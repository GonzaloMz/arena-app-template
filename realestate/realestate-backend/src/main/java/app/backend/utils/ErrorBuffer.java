package app.backend.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class ErrorBuffer {
	
	ArrayList<String> errors;
	
	public ErrorBuffer() {
		errors=new ArrayList<String>();
	}
	
	public void append(String prefix, String[] errors) {
		if(errors==null)
			return;
		this.errors.addAll(Arrays.asList(errors));
		this.errors.removeIf(s->StringUtils.isBlank(s));
	}

	public String[] getErrors() {
		return errors.toArray(new String[0]);
	}

	public void append(String[] validate) {
		this.append(null,validate);
	}

}
