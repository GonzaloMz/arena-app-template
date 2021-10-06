package app.backend.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

import arena.backend.model.AbstractEntity;
import arena.backend.model.extension.AbstractDataTransferObject;
import arena.backend.model.extension.Hidden;

@MappedSuperclass
public class LogInFieldsDTO extends AbstractEntity implements AbstractDataTransferObject {

	@Transient
	private String token;
	
	@CreatedDate
	private Date creationDate;

	public String getToken() {
		return token;
	}

	@Hidden
	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	@Hidden
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
