/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Place;
import app.backend.repository.PlaceRepository;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class PlaceService extends ArenaService<Place,Place>{
	
	@Autowired
	PlaceRepository placeRepository;

	@Override
	protected JpaRepository<Place, Long> getRepository() {
		return placeRepository;
	}

	@Override
	public Place create(Optional<Place> ent) {
		
		Place place = ent.get();
		return placeRepository.save(place);
	}

	@Override
	public List<Place> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
