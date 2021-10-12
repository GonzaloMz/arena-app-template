package app.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>, JpaSpecificationExecutor<Rent>{

	boolean existsByEstateAndCheckInDateBetweenAndActiveIsTrue(Long estateId, Date checkIn, Date checkOut);

	boolean existsByEstateAndCheckOutDateBetweenAndActiveIsTrue(Long estateId, Date checkIn, Date checkOut);

	List<Rent> findByCheckOutDateAfterAndActiveIsTrue(Date now);

}
