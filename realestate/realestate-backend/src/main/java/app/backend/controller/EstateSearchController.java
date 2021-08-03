/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.EstateSearch;
import app.backend.service.EstateSearchService;
import arena.backend.controller.ArenaController;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/estateSearch")
public class EstateSearchController extends ArenaController<EstateSearch,EstateSearch>{
		
	@Autowired
	private EstateSearchService estateSearchService;

	@Override
	public ArenaService<EstateSearch,EstateSearch> getService() {
		return this.estateSearchService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(EstateSearch.class);
	}
	
}
