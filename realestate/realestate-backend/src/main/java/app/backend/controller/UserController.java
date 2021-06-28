/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.User;
import app.backend.service.UserService;
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
@RequestMapping(value = "/user")
public class UserController extends ArenaController<User,User>{
		
	@Autowired
	private UserService userService;

	@Override
	public ArenaService<User,User> getService() {
		return this.userService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(User.class);
	}
	
}
