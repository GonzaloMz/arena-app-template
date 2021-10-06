/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.PlaceInventory;
import app.backend.service.PlaceInventoryService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/placeInventory")
public class PlaceInventoryController extends ArenaController<PlaceInventory,PlaceInventory>{
		
	@Autowired
	private PlaceInventoryService placeInventoryService;

	@Override
	public ArenaService<PlaceInventory,PlaceInventory> getService() {
		return this.placeInventoryService;
	}

}
