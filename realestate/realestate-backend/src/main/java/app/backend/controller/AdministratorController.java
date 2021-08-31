/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.Administrator;
import app.backend.service.AdministratorService;
import arena.backend.controller.ArenaController;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/administrator")
public class AdministratorController extends ArenaController<Administrator,Administrator>{
		
	@Autowired
	private AdministratorService administratorService;

	@Override
	public ArenaService<Administrator,Administrator> getService() {
		return this.administratorService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Administrator.class);
	}

	@Override
	public Administrator get(Long id) {
		return null;
	}

	@Override
	public List<Administrator> getAll(List<Long> ids) {
		return null;
	}

	@Override
	public List<Administrator> searchByExample(Administrator entity) {
		return null;
	}

	@Override
	public List<Administrator> searchBySpecification(Map<String, String> arg0) throws Exception {
		return null;
	}

	@Override
	public List<Administrator> searchInLine(String query) {
		return null;
	}
	
	
	
}
