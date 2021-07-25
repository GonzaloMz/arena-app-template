/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.PlaceDescription;
import app.backend.service.PlaceDescriptionService;
import arena.backend.controller.ArenaController;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/placeDescription")
public class PlaceDescriptionController extends ArenaController<PlaceDescription, PlaceDescription>{
		
	@Autowired
	private PlaceDescriptionService placeDescriptionService;

	@Override
	public ArenaService<PlaceDescription, PlaceDescription> getService() {
		return this.placeDescriptionService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(PlaceDescription.class);
	}
	
}
