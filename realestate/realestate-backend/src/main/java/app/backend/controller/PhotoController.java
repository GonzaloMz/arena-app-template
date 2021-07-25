/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.controller;

import app.backend.model.Photo;
import app.backend.service.PhotoService;
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
@RequestMapping(value = "/photo")
public class PhotoController extends ArenaController<Photo,Photo>{
		
	@Autowired
	private PhotoService photoService;

	@Override
	public ArenaService<Photo,Photo> getService() {
		return this.photoService;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Photo.class);
	}
	
}
