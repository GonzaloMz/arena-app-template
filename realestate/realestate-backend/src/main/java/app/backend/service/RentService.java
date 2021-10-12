/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.backend.dto.RentDTO;
import app.backend.exception.RealestateException;
import app.backend.model.Estate;
import app.backend.model.Owner;
import app.backend.model.Rent;
import app.backend.model.enums.EstateOperations;
import app.backend.repository.RentRepository;
import app.backend.utils.DateUtils;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

/**
 *
 * @author 
 */
@Component
public class RentService extends ArenaService<Rent,RentDTO>{
	
	private static final String[] INVALID_OPERATION_ERROR = {"rent.estate.operation.invalid"};

	private static final String[] TAKEN_DATE_RANGE_ERROR = {"rent.estate.daterange.taken"};
	
	private static final String[] INVALID_DATE_RANGE_ERROR = {"rent.estate.daterange.invalid"};

	@Autowired
	RentRepository rentRepository;
	
	@Autowired
	EstateService estateService;
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	TemporaryRentPriceService temporaryRentPriceService;
	
	
	@Override
	protected JpaRepository<Rent, Long> getRepository() {
		return rentRepository;
	}
	
	@Override
	public List<Rent> searchInLine(String query) {
		return null;
	}
	
	

	@Override
	public Rent create(Optional<RentDTO> optional) {
		RentDTO rent = optional.get();
		Date checkIn=DateUtils.getLastMomentOfDay(rent.getCheckInDate());
		Date checkOut=DateUtils.getFirstMomentOfDay(rent.getCheckOutDate());
		Long estateId = rent.getEstate();
		verifyDates(checkIn,checkOut);
		if(rentRepository.existsByEstateAndCheckInDateBetweenAndActiveIsTrue(estateId , checkIn, checkOut) ||
				rentRepository.existsByEstateAndCheckOutDateBetweenAndActiveIsTrue(estateId, checkIn, checkOut)) {
			throw new RealestateException(TAKEN_DATE_RANGE_ERROR);
		}
		rent.setActive(true);
		Rent rentEntity = new Rent();
		BeanUtils.copyProperties(rent, rentEntity, "id");
		fillPrice(estateId, checkIn, checkOut, rentEntity);
		
		Owner tenant = this.ownerService.create(rent.getTenant());
		rentEntity.setTenant(tenant.getId());
		
		return this.rentRepository.save(rentEntity);
	}

	private void verifyDates(Date checkIn, Date checkOut) {
		if(checkIn.after(checkOut))
			throw new RealestateException(INVALID_DATE_RANGE_ERROR);
	}

	@Override
	public RentDTO buildTemplate(Map<String, String> parameters) {
		RentDTO rentTemplate= new RentDTO();
		Long estateId = Long.valueOf(parameters.get("estate"));
		Estate estate=estateService.get(estateId);
		if(!EstateOperations.RENT.equals(estate.getOperation()))
			throw new RealestateException(INVALID_OPERATION_ERROR);
		try {
			Date checkIn =DateUtils.createDate(parameters.get("checkInDate"));
			Date checkOut =DateUtils.createDate(parameters.get("checkOutDate"));
			if(checkIn!=null && checkOut!=null) {
				verifyDates(checkIn, checkOut);
				checkIn=DateUtils.getLastMomentOfDay(checkIn);
				checkOut=DateUtils.getFirstMomentOfDay(checkOut);
				if(!rentRepository.existsByEstateAndCheckInDateBetweenAndActiveIsTrue(estateId, checkIn, checkOut) &&
						!rentRepository.existsByEstateAndCheckOutDateBetweenAndActiveIsTrue(estateId, checkIn, checkOut)) {
					rentTemplate.setCheckInDate(checkIn);
					rentTemplate.setCheckOutDate(checkOut);
				}
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rentTemplate.setEstate(estateId);
		return rentTemplate;
	}
	
	public List<Date> getNotAvailableDates(Long estateId){
		List<Date> notAvailableDates=new ArrayList<>();
		List<Rent> rents = this.rentRepository.findByCheckOutDateAfterAndActiveIsTrue(DateUtils.now());
		for (Rent rent : rents) {
			Date in = DateUtils.getFirstMomentOfDay(rent.getCheckInDate());
			Date out = DateUtils.getFirstMomentOfDay(rent.getCheckOutDate());
			long daysBetweenDates = DateUtils.getDaysBetweenDates(in, out);
			for (int i = 1; i <= daysBetweenDates; i++) {
				notAvailableDates.add(DateUtils.addDaysToDate(in, i));
			}
		}
		return notAvailableDates;
	}
	
	public Rent fillPrice(Long estateId, Date checkIn, Date checkOut) {
		Rent rent = new Rent();
		return fillPrice(estateId, checkIn, checkOut, rent);
	}

	private Rent fillPrice(Long estateId, Date checkIn, Date checkOut, Rent rent) {
		rent.setEstate(estateId);
		Estate estate=estateService.get(estateId);
		long daysBetweenDates = DateUtils.getDaysBetweenDates(checkIn, checkOut);
		rent.setStayTotal(temporaryRentPriceService.getStayTotal(estate.getTemporaryRentPrice(), daysBetweenDates));
		return rent;
	}

	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(Rent.class);
	}
	
}
