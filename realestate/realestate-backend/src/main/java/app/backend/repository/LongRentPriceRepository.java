package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.LongRentPrice;

public interface LongRentPriceRepository extends JpaRepository<LongRentPrice, Long>, JpaSpecificationExecutor<LongRentPrice>{

}
