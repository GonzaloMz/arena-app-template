/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.Owner;
import app.backend.service.OwnerService;
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
@RequestMapping(value = "/owner")
public class OwnerController extends ArenaController<Owner,Owner>{
		
	@Autowired
	private OwnerService ownerService;

	@Override
	public ArenaService<Owner,Owner> getService() {
		return this.ownerService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Owner.class);
	}
	
}
