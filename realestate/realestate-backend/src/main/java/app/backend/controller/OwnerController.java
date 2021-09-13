/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.backend.model.Owner;
import app.backend.model.dto.OwnerDTO;
import app.backend.service.OwnerService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/owner")
public class OwnerController extends ArenaController<Owner,OwnerDTO>{
		
	@Autowired
	private OwnerService ownerService;

	@Override
	public ArenaService<Owner,OwnerDTO> getService() {
		return this.ownerService;
	}

}
