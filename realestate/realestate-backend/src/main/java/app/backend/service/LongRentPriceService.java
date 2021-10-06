/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.LongRentPrice;
import app.backend.repository.LongRentPriceRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class LongRentPriceService extends ArenaService<LongRentPrice,LongRentPrice>{
	
	@Autowired
	LongRentPriceRepository longRentPriceRepository;

	@Override
	protected JpaRepository<LongRentPrice, Long> getRepository() {
		return longRentPriceRepository;
	}
	
	@Override
	public List<LongRentPrice> searchInLine(String query) {
		return null;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(LongRentPrice.class);
	}
	
}
