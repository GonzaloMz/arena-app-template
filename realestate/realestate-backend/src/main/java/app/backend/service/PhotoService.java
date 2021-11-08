/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Photo;
import app.backend.repository.PhotoRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
public class PhotoService extends ArenaService<Photo,Photo>{
	
	@Autowired
	PhotoRepository photoRepository;

	@Override
	protected JpaRepository<Photo, Long> getRepository() {
		return photoRepository;
	}
	
	@Override
	public List<Photo> searchInLine(String query) {
		return null;
	}

	@Override
	public Photo buildTemplate(Map<String, String> parameters) {
		Photo p = new Photo();
		p.setPlace(Long.valueOf(parameters.get("place")));
		return p;
	}

	@Override
	public Photo create(Optional<Photo> optional) {
		if(optional.get().getView()==null)
			return null;
		String[] base64Image = optional.get().getView().split(",");
		String extention = base64Image[0].split("/")[1].split(";")[0];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image[1]);

		BufferedImage image = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
		try {
			image = ImageIO.read(bis);
			bis.close();
			String path = "/images/"+UUID.randomUUID()+"."+extention;
			File outputfile = new File(path);
			ImageIO.write(image, extention, outputfile);
			optional.get().setView(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.create(optional);
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Photo.class);
	}

	public long countBySpecification(HashMap<String, String> photos) {
		Specification<Photo> specifications = null;
		try {
			specifications = this.specBuilder.buildCriterio(photos, this.getShape());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long result = getExecutorRepository().count(specifications);
		return result;
	}
	

}
