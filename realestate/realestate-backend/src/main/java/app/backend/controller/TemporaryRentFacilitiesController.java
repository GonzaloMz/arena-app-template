/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.TemporaryRentFacilities;
import app.backend.service.TemporaryRentFacilitiesService;
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
@RequestMapping(value = "/temporaryRentFacilities")
public class TemporaryRentFacilitiesController extends ArenaController<TemporaryRentFacilities,TemporaryRentFacilities>{
		
	@Autowired
	private TemporaryRentFacilitiesService temporaryRentFacilitiesService;

	@Override
	public ArenaService<TemporaryRentFacilities,TemporaryRentFacilities> getService() {
		return this.temporaryRentFacilitiesService;
	}

}
