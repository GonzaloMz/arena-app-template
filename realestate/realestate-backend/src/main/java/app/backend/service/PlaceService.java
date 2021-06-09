/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Place;
import app.backend.repository.PlaceRepository;
import arena.backend.service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class PlaceService extends ArenaService<Place>{
	
	@Autowired
	PlaceRepository placeRepository;

	@Override
	protected JpaRepository<Place, Long> getRepository() {
		return placeRepository;
	}

	@Override
	public Place create() {
		return new Place();
	}
	
}
