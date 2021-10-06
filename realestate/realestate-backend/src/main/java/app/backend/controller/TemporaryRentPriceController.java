/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.TemporaryRentPrice;
import app.backend.service.TemporaryRentPriceService;
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
@RequestMapping(value = "/temporaryRentPrice")
public class TemporaryRentPriceController extends ArenaController<TemporaryRentPrice,TemporaryRentPrice>{
		
	@Autowired
	private TemporaryRentPriceService temporaryRentPriceService;

	@Override
	public ArenaService<TemporaryRentPrice,TemporaryRentPrice> getService() {
		return this.temporaryRentPriceService;
	}

}
