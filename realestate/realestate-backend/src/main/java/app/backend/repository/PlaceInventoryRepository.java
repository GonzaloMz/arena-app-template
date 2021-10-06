package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.PlaceInventory;

public interface PlaceInventoryRepository extends JpaRepository<PlaceInventory, Long>, JpaSpecificationExecutor<PlaceInventory>{

}
