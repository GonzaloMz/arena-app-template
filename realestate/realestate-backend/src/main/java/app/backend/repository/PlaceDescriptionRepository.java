package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.PlaceDescription;

public interface PlaceDescriptionRepository extends JpaRepository<PlaceDescription, Long>, JpaSpecificationExecutor<PlaceDescription>{

}
