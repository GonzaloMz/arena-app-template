/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.Owner;
import app.backend.model.Place;
import app.backend.model.dto.OwnerDTO;
import app.backend.repository.OwnerRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author 
 */
@Component
public class OwnerService extends ArenaService<Owner,OwnerDTO>{
	
	ObjectMapper mapper=new ObjectMapper();
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	PlaceService placeService;

	@Override
	protected JpaRepository<Owner, Long> getRepository() {
		return ownerRepository;
	}
	
	@Override
	public List<Owner> searchInLine(String query) {
		return null;
	}

	@Override
	public Owner create(Optional<OwnerDTO> optional) {
		Owner o = this.ownerRepository.findByUser(optional.get().getUser());
		if(o==null)
			o= new Owner();
		BeanUtils.copyProperties(optional.get(), o, "id");
		Place p = new Place();
		BeanUtils.copyProperties(optional.get().getAddress(), p,"id");
		placeService.create(Optional.of(p));
		o.setAddress(p.getId());
		return this.ownerRepository.save(o);
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Owner.class);
	}
	
}
