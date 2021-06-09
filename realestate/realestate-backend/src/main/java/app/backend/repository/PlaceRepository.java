package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place>{

}
