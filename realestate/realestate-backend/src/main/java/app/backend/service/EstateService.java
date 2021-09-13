/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.backend.model.Assessment;
import app.backend.model.Estate;
import app.backend.model.Owner;
import app.backend.model.dto.EstateDTO;
import app.backend.model.dto.OwnerDTO;
import app.backend.repository.EstateRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@Component
public class EstateService extends ArenaService<Estate,EstateDTO>{
	
	@Autowired
	EstateRepository estateRepository;
	
	@Autowired
	AssessmentService assessmentService;
	
	@Autowired
	OwnerService ownerService;

	@Override
	protected JpaRepository<Estate, Long> getRepository() {
		return estateRepository;
	}

	@Override
	public Estate create(Optional<EstateDTO> ent) {
		
		Estate est = new Estate();
		BeanUtils.copyProperties(ent.get(), est,"id", "owner");
		Owner owner = ownerService.create(Optional.of(ent.get().getOwner()));
		est.setOwner(owner.getId());
		return this.estateRepository.save(est);
	}

	@Override
	public List<Estate> searchInLine(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstateDTO buildTemplate(Map<String, String> parameters) {
		Long assessment = Long.valueOf(parameters.get("assessment"));
		Assessment assessmentEntity = assessmentService.get(assessment);
		EstateDTO estate = new EstateDTO();
		if (assessmentEntity!=null) {
			BeanUtils.copyProperties(assessmentEntity, estate,"id");
			OwnerDTO owner = new OwnerDTO();
			owner.setUser(assessmentEntity.getUserId());
			estate.setOwner(owner);
		}
		return estate;
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Estate.class);
	}
	
	
}
