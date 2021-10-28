/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.dto.RentDTO;
import app.backend.model.Rent;
import app.backend.service.RentService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/rent")
public class RentController extends ArenaController<Rent,RentDTO>{
		
	@Autowired
	private RentService rentService;

	@Override
	public ArenaService<Rent,RentDTO> getService() {
		return this.rentService;
	}

	@GetMapping(path="/notAvailableDates/{estate}")
	public List<Date> notAvailableDates(@PathVariable Long estate){
		return this.rentService.getNotAvailableDates(estate);
	}
	
	@GetMapping(path="/fillPrice")
	public Rent fillPrice(@RequestParam("estate") Long estate,
			@RequestParam("checkInDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkIn,
			@RequestParam("checkOutDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOut){
		return this.rentService.fillPrice(estate, checkIn, checkOut);
	}
}
