/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.dto.OwnerDTO;
import app.backend.dto.OwnerWithUserDTO;
import app.backend.exception.RealestateException;
import app.backend.model.Owner;
import app.backend.model.Place;
import app.backend.model.User;
import app.backend.repository.OwnerRepository;
import app.backend.utils.ErrorBuffer;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaCreateResponse;
import arena.backend.service.ArenaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
	
	@Autowired
	UserService userService;

	@Override
	protected JpaRepository<Owner, Long> getRepository() {
		return ownerRepository;
	}
	
	@Override
	public List<Owner> searchInLine(String query) {
		if (StringUtils.isEmpty(query)) {
			return new ArrayList<>();
		}
		Long longValueQuery =null;
		try {
			longValueQuery=Long.valueOf(query);
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return this.ownerRepository.findByDni(longValueQuery);
	}

	@Override
	@Transactional
	public Owner create(Optional<OwnerDTO> optional) {
		Owner o = this.ownerRepository.findByUser(optional.get().getUser());
		if(o==null)
			o= new Owner();
		BeanUtils.copyProperties(optional.get(), o, "id");
		ErrorBuffer errors = new ErrorBuffer();
		ArenaCreateResponse<Place> p = placeService.validateAndCreate(optional.get().getAddress());
		errors.append("address", p.getErrors());
		o.setAddress(p.getEntityId());
		errors.append("owner", this.validate(o));
		
		if(errors.getErrors().length>0) {
			throw new RealestateException(errors.getErrors());
		}
//		BeanUtils.copyProperties(optional.get().getAddress(), p,"id");
//		placeService.create(Optional.of(p));
		return this.ownerRepository.save(o);
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Owner.class);
	}

	public Owner create(OwnerWithUserDTO tenant) {
		if(tenant.getId()!=null) {
			return this.get(tenant.getId());
		}
		OwnerDTO ownerDto = this.getMapper().convertValue(tenant, OwnerDTO.class);
		if(tenant.getUserDTO().getId()!=null) {
			ownerDto.setUser(tenant.getUserDTO().getId());
		} else {
			User user = userService.create(Optional.of(tenant.getUserDTO()));
			ownerDto.setUser(user.getId());
		}
		return this.create(Optional.of(ownerDto));
	}
	
}
