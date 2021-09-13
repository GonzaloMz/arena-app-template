/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.backend.model.Place;
import app.backend.service.PlaceService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/place")
public class PlaceController extends ArenaController<Place,Place>{
		
	@Autowired
	private PlaceService placeService;

	@Override
	public ArenaService<Place,Place> getService() {
		return this.placeService;
	}


}
