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
		if(StringUtils.isNotBlank(prefix))
			prefix+=".";
		else
			prefix="";
		for (int i = 0; i < errors.length; i++) {
			errors[i]=prefix+errors[i];
		}
		this.errors.addAll(Arrays.asList(errors));
		this.errors.removeIf(s->StringUtils.isBlank(s));
	}

	public String[] getErrors() {
		return errors.toArray(new String[0]);
	}

	public void append(String[] validate) {
		this.append(null,validate);
	}

	public void append(String string) {
		this.append(null,string.split(";"));
		
	}

}
