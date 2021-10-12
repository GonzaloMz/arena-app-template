/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.backend.model.Estate;
import app.backend.model.EstateSearch;
import app.backend.repository.EstateRepository;
import app.backend.repository.EstateSearchRepository;
import arena.backend.model.extension.Shape;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@Component
public class EstateSearchService extends ArenaService<EstateSearch,EstateSearch>{
//	
//	@Autowired
//	EstateRepository estateSearchRepository;
	@Autowired
	EstateRepository estateRepository;

	@Override
	protected JpaRepository<EstateSearch, Long> getRepository() {
		return null;
	}
	
	@Override
	public List<EstateSearch> searchInLine(String query) {
		return null;
	}

	@Override
	public List<EstateSearch> searchBySpecification(Map<String, String> parameters, Shape shape) throws ParseException {
		EstateSearch criteria = getMapper().convertValue(parameters, EstateSearch.class);
		List<Estate> estates = estateRepository.findEstates(
				criteria.getEnvironments(),
				criteria.getEstateType(),
				criteria.getOperation(),
				criteria.getPrice());
		return estates.stream().map(e->new EstateSearch(e)).collect(Collectors.toList());
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(EstateSearch.class);
	}
		
}
