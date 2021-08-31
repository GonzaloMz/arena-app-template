/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Administrator;
import app.backend.repository.AdministratorRepository;
import arena.backend.service.ArenaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class AdministratorService extends ArenaService<Administrator,Administrator>{
	
	@Autowired
	AdministratorRepository administratorRepository;

	@Override
	protected JpaRepository<Administrator, Long> getRepository() {
		return administratorRepository;
	}
	
	@Override
	public List<Administrator> searchInLine(String query) {
		return null;
	}

	
}
