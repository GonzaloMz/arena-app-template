/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.TemporaryRentPrice;
import app.backend.repository.TemporaryRentPriceRepository;
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
public class TemporaryRentPriceService extends ArenaService<TemporaryRentPrice,TemporaryRentPrice>{
	
	@Autowired
	TemporaryRentPriceRepository temporaryRentPriceRepository;

	@Override
	protected JpaRepository<TemporaryRentPrice, Long> getRepository() {
		return temporaryRentPriceRepository;
	}
	
	@Override
	public List<TemporaryRentPrice> searchInLine(String query) {
		return null;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(TemporaryRentPrice.class);
	}

	public Double getStayTotal(Long temporaryRentPriceId, long daysBetweenDates) {
		TemporaryRentPrice price = this.temporaryRentPriceRepository.getOne(temporaryRentPriceId);
		Double result =null;
		if(daysBetweenDates>=TemporaryRentPrice.STAY_DURATION_IN_DAYS)
			result= price.getStayDailyPrice()*daysBetweenDates;
		else if(daysBetweenDates>=TemporaryRentPrice.STAY_DURATION_IN_DAYS/2)
			result= price.getHalfStayDailyPrice()*daysBetweenDates;
		else
			result= price.getDailyPrice()*daysBetweenDates;
		return Math.ceil( result);
	}
	
}
