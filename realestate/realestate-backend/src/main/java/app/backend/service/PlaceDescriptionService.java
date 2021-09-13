/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.PlaceDescription;
import app.backend.repository.PlaceDescriptionRepository;
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
public class PlaceDescriptionService extends ArenaService<PlaceDescription, PlaceDescription>{
	
	@Autowired
	PlaceDescriptionRepository placeDescriptionRepository;

	@Override
	protected JpaRepository<PlaceDescription, Long> getRepository() {
		return placeDescriptionRepository;
	}

	@Override
	public List<PlaceDescription> searchInLine(String query) {
		return null;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(PlaceDescription.class);
	}
	
	
}
