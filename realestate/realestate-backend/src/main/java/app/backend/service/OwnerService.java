/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Owner;
import app.backend.repository.OwnerRepository;
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
public class OwnerService extends ArenaService<Owner,Owner>{
	
	@Autowired
	OwnerRepository ownerRepository;

	@Override
	protected JpaRepository<Owner, Long> getRepository() {
		return ownerRepository;
	}
	
	@Override
	public List<Owner> searchInLine(String query) {
		return null;
	}

}
