/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Estate;
import app.backend.repository.EstateRepository;
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
public class EstateService extends ArenaService<Estate,Estate>{
	
	@Autowired
	EstateRepository estateRepository;

	@Override
	protected JpaRepository<Estate, Long> getRepository() {
		return estateRepository;
	}

	@Override
	public Estate create(Optional<Estate> ent) {
		return new Estate();
	}

	@Override
	public List<Estate> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
