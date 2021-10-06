package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.TemporaryRentFacilities;

public interface TemporaryRentFacilitiesRepository extends JpaRepository<TemporaryRentFacilities, Long>, JpaSpecificationExecutor<TemporaryRentFacilities>{

}
