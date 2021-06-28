package app.backend.model.base;


import app.backend.model.enums.UserStatus;
import arena.backend.model.extension.RegEx;
import arena.backend.model.extension.Required;

public interface BasicUser{

	public String getName();


	@RegEx(expression = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
	@Required
	public String getEmail();


	public String getLastName();

	public String getPhone() ;

	public UserStatus getStatus();
	
	
}
