/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.PlaceInventory;
import app.backend.repository.PlaceInventoryRepository;
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
public class PlaceInventoryService extends ArenaService<PlaceInventory,PlaceInventory>{
	
	@Autowired
	PlaceInventoryRepository placeInventoryRepository;

	@Override
	protected JpaRepository<PlaceInventory, Long> getRepository() {
		return placeInventoryRepository;
	}
	
	@Override
	public List<PlaceInventory> searchInLine(String query) {
		return null;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(PlaceInventory.class);
	}
	
}
