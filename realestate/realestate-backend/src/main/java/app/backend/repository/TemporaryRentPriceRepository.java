package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.TemporaryRentPrice;

public interface TemporaryRentPriceRepository extends JpaRepository<TemporaryRentPrice, Long>, JpaSpecificationExecutor<TemporaryRentPrice>{

}
