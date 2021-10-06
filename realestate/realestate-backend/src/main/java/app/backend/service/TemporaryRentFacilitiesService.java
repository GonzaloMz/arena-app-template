/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.TemporaryRentFacilities;
import app.backend.repository.TemporaryRentFacilitiesRepository;
import arena.backend.model.extension.ShapeFactory;
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
public class TemporaryRentFacilitiesService extends ArenaService<TemporaryRentFacilities,TemporaryRentFacilities>{
	
	@Autowired
	TemporaryRentFacilitiesRepository temporaryRentFacilitiesRepository;

	@Override
	protected JpaRepository<TemporaryRentFacilities, Long> getRepository() {
		return temporaryRentFacilitiesRepository;
	}
	
	@Override
	public List<TemporaryRentFacilities> searchInLine(String query) {
		return null;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(TemporaryRentFacilities.class);
	}
	
}
