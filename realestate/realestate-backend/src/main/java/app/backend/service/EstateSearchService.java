/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.EstateSearch;
import app.backend.repository.EstateSearchRepository;
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
public class EstateSearchService extends ArenaService<EstateSearch,EstateSearch>{
	
	@Autowired
	EstateSearchRepository estateSearchRepository;

	@Override
	protected JpaRepository<EstateSearch, Long> getRepository() {
		return estateSearchRepository;
	}
	
	@Override
	public List<EstateSearch> searchInLine(String query) {
		return null;
	}

}
