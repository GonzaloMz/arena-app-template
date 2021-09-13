/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.backend.model.LogIn;
import app.backend.model.dto.LogInDTO;
import app.backend.service.LogInService;
import arena.backend.controller.ArenaController;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@RestController
@RequestMapping(value = "/logIn")
public class LogInController extends ArenaController<LogIn,LogInDTO>{
		
	@Autowired
	private LogInService logInService;

	@Override
	public ArenaService<LogIn,LogInDTO> getService() {
		return this.logInService;
	}


}
