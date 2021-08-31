/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Administrator;
import app.backend.model.LogIn;
import app.backend.model.dto.LogInDTO;
import app.backend.repository.LogInRepository;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class LogInService extends ArenaService<LogIn,LogInDTO>{
	
	@Autowired
	LogInRepository logInRepository;
	
	@Autowired
	AdministratorService administratorService;
	
	private enum Errors {
		ADMINMISTRATOR_NOT_FOUND;
	}

	@Override
	protected JpaRepository<LogIn, Long> getRepository() {
		return logInRepository;
	}
	
	@Override
	public List<LogIn> searchInLine(String query) {
		return null;
	}

	@Override
	public LogIn create(Optional<LogInDTO> optional) {
		List<Administrator> admin=administratorService.get(optional.get().getAdministrator());
		if(admin.isEmpty())
			throw new RuntimeException(Errors.ADMINMISTRATOR_NOT_FOUND.toString());
		LogIn login= new LogIn();
		Administrator administrator = admin.get(0);
		login.setAdministrator(administrator.getId());
		login=logInRepository.save(login);
		login.setToken("token-"+administrator.getUsername());
		return login;
	}
	
	

}
