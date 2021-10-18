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

import app.backend.dto.EstateDTO;
import app.backend.dto.OwnerDTO;
import app.backend.exception.RealestateException;
import app.backend.model.Assessment;
import app.backend.model.Estate;
import app.backend.model.LongRentAssessment;
import app.backend.model.LongRentPrice;
import app.backend.model.Owner;
import app.backend.model.PlaceInventory;
import app.backend.model.TemporaryRentAssessment;
import app.backend.model.TemporaryRentFacilities;
import app.backend.model.TemporaryRentPrice;
import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateStatus;
import app.backend.repository.EstateRepository;
import app.backend.utils.ErrorBuffer;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaCreateResponse;
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
	
	@Autowired
	PlaceInventoryService placeInventoryService;
	
	@Autowired
	TemporaryRentAssessmentService temporaryRentAssessmentService;
	
	@Autowired
	TemporaryRentFacilitiesService temporaryRentFacilitiesService;
	
	@Autowired
	TemporaryRentPriceService temporaryRentPriceService;
	
	@Autowired
	LongRentPriceService longRentPriceService;
	
	@Autowired
	LongRentAssessmentService longRentAssessmentService;

	@Override
	protected JpaRepository<Estate, Long> getRepository() {
		return estateRepository;
	}

	@Override
	@Transactional
	public Estate create(Optional<EstateDTO> ent) {
		Estate est = new Estate();
		EstateDTO estateDTO = ent.get();
		BeanUtils.copyProperties(estateDTO, est,"id", "owner");
		ErrorBuffer errors = new ErrorBuffer();
		errors.append("estate", this.validate(est));
		
		ArenaCreateResponse<Owner> ownerCreateResponse = ownerService.validateAndCreate(estateDTO.getOwner());
		errors.append("owner", ownerCreateResponse.getErrors());
		ArenaCreateResponse<PlaceInventory> placeInventoryCreateResponse = placeInventoryService.validateAndCreate(estateDTO.getPlaceInventory());
		errors.append("placeInventory",placeInventoryCreateResponse.getErrors());
		ArenaCreateResponse<TemporaryRentFacilities> facilitiesCreateResponse = temporaryRentFacilitiesService.validateAndCreate(estateDTO.getTemporaryRentFacilities());
		errors.append("temporaryRentFacilities", facilitiesCreateResponse.getErrors());
		ArenaCreateResponse<TemporaryRentPrice> temporaryRentPriceCreateResponse = temporaryRentPriceService.validateAndCreate(estateDTO.getTemporaryRentPrice());
		errors.append("temporaryRentPrice", temporaryRentPriceCreateResponse.getErrors());
		ArenaCreateResponse<LongRentPrice> longRentPriceCreateResponse = longRentPriceService.validateAndCreate(estateDTO.getLongRentPrice());
		errors.append("longRentPrice",longRentPriceCreateResponse.getErrors());

		if(errors.getErrors().length>0) {
			throw new RealestateException(errors.getErrors());
		}

		this.updateAssessment(est.getPlaceId(), est.getOperation(), est.getStatus());
		est.setOwner(ownerCreateResponse.getEntityId());
		est.setPlaceInventory(placeInventoryCreateResponse.getEntityId());
		est.setTemporaryRentFacilities(facilitiesCreateResponse.getEntityId());
		est.setTemporaryRentPrice(temporaryRentPriceCreateResponse.getEntityId());
		est.setLongRentPrice(longRentPriceCreateResponse.getEntityId());
		
		setPrice(est, temporaryRentPriceCreateResponse, longRentPriceCreateResponse);
		
		return this.estateRepository.save(est);
	}

	private void setPrice(Estate est, ArenaCreateResponse<TemporaryRentPrice> temporaryRentPriceCreateResponse,
			ArenaCreateResponse<LongRentPrice> longRentPriceCreateResponse) {
		switch (est.getOperation()) {
		case RENT:
			est.setPrice(temporaryRentPriceCreateResponse.getEntity().getStayPrice());
			break;
		case LONG_RENT:
			est.setPrice(longRentPriceCreateResponse.getEntity().getMontlyPrice());
			break;
		case SALE:
			est.setPrice(est.getSalePrice());
		default:
			break;
		}
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
	

	@Override
	public List<Estate> searchInLine(String query) {
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
			if(assessmentEntity.getPlaceInventory()!=null) {				
				// creo la copia del place inventory
				PlaceInventory inventory = placeInventoryService.get(assessmentEntity.getPlaceInventory());
				estate.setPlaceInventory(new PlaceInventory());
				BeanUtils.copyProperties(inventory, estate.getPlaceInventory(),"id");
			}
			
			if(EstateOperations.RENT.equals(assessmentEntity.getOperation())) {
				estate.setTemporaryRentFacilities(new TemporaryRentFacilities());
				TemporaryRentAssessment temporaryRentSuggestedPrices = temporaryRentAssessmentService.get(assessmentEntity.getTemporaryRentAssessment());
				estate.setTemporaryRentPrice(new TemporaryRentPrice(temporaryRentSuggestedPrices));
				TemporaryRentFacilities temporaryRentFacilities = temporaryRentFacilitiesService.get(temporaryRentSuggestedPrices.getTemporaryRentFacilities());
				estate.setTemporaryRentFacilities(new TemporaryRentFacilities());
				BeanUtils.copyProperties(temporaryRentFacilities, estate.getTemporaryRentFacilities(),"id");
			}
			estate.setAssessmentTimestamp(assessmentEntity.getTimestamp());
			if(EstateOperations.SALE.equals(assessmentEntity.getOperation())) {				
				estate.setSalePrice(assessmentEntity.getSaleSuggestedValue());
			}
			if(EstateOperations.LONG_RENT.equals(assessmentEntity.getOperation())) {
				LongRentPrice longRentPrice = new LongRentPrice();
				LongRentAssessment longRentAssessment = longRentAssessmentService.get(assessmentEntity.getLongRentAssessment()); 
				longRentPrice.setMontlyPrice(longRentAssessment.getMontlySuggestedPrice());
				longRentPrice.setPriceAdjustment(longRentAssessment.getSuggestedPriceAdjustment());
				estate.setLongRentPrice(longRentPrice);
			}
			estate.setSugestedValue(assessmentEntity.getSugestedValue());
		}
		return estate;
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Estate.class);
	}
	
	
}
