/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.LongRentPrice;
import app.backend.service.LongRentPriceService;
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
@RequestMapping(value = "/longRentPrice")
public class LongRentPriceController extends ArenaController<LongRentPrice,LongRentPrice>{
		
	@Autowired
	private LongRentPriceService longRentPriceService;

	@Override
	public ArenaService<LongRentPrice,LongRentPrice> getService() {
		return this.longRentPriceService;
	}

}
