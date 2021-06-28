/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.Estate;
import app.backend.service.EstateService;
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
@RequestMapping(value = "/estate")
public class EstateController extends ArenaController<Estate,Estate>{
		
	@Autowired
	private EstateService estateService;

	@Override
	public ArenaService<Estate,Estate> getService() {
		return this.estateService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Estate.class);
	}
	
}
