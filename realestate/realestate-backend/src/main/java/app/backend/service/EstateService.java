/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.backend.exception.RealestateException;
import app.backend.model.Assessment;
import app.backend.model.Estate;
import app.backend.model.Owner;
import app.backend.model.dto.EstateDTO;
import app.backend.model.dto.OwnerDTO;
import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateStatus;
import app.backend.repository.EstateRepository;
import app.backend.utils.ErrorBuffer;
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
	PhotoService photoService;
	
	@Autowired
	OwnerService ownerService;

	@Override
	protected JpaRepository<Estate, Long> getRepository() {
		return estateRepository;
	}

	@Override
	@Transactional
	public Estate create(Optional<EstateDTO> ent) {
		
		Estate est = new Estate();
		BeanUtils.copyProperties(ent.get(), est,"id", "owner");
		ErrorBuffer errors = new ErrorBuffer();
		errors.append("estate", this.validate(est));
		Owner owner = ownerService.create(Optional.of(ent.get().getOwner()));
		errors.append("owner", ownerService.validate(owner));
//		long photos = photoService.countBySpecification(ent.get().getPhotos());
//		if(photos==0)
//			errors.append("estate.photo.required");
		if(errors.getErrors().length>0) {
			throw new RealestateException(errors.getErrors());
		}
		this.updateAssessment(est.getPlaceId(), est.getOperation(), est.getStatus());
		est.setOwner(owner.getId());
		return this.estateRepository.save(est);
	}

	private void updateAssessment(Long placeId, EstateOperations operation, EstateStatus estateStatus) {
		Assessment a = new Assessment();
		a.setPlaceId(placeId);
		a.setOperation(operation);
		List<Assessment> apps = assessmentService.get(a);
		if(!apps.isEmpty()) {
			Assessment first = apps.get(0);
			first.setEstateCreated(true);
			assessmentService.update(first);
		}
	}
	
	
//
//	@Override
//	public Estate update(Estate entity) {
//		boolean updateAssessment = entity.getStatus() != null;
//		entity =  super.update(entity);
//		if(updateAssessment)
//			this.updateAssessment(entity.getPlaceId(), entity.getOperation(), entity.getStatus());
//		return entity;
//	}

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
		estate.setAssessmentTimestamp(assessmentEntity.getTimestamp());
		estate.setSugestedValue(assessmentEntity.getSugestedValue());
		return estate;
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Estate.class);
	}
	
	
}
