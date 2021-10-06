/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.backend.dto.EstateDTO;
import app.backend.model.Estate;
import app.backend.service.EstateService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/estate")
public class EstateController extends ArenaController<Estate,EstateDTO>{
		
	@Autowired
	private EstateService estateService;

	@Override
	public ArenaService<Estate,EstateDTO> getService() {
		return this.estateService;
	}

}
