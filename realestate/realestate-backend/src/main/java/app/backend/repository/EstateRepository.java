package app.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.backend.model.Estate;
import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;

public interface EstateRepository extends JpaRepository<Estate, Long>, JpaSpecificationExecutor<Estate>{

	@Query("select e from Estate e "
			+ "where (e.estateType = :estateType or :estateType is null) and "
			+ "(e.price = :price or :price is null) and "
			+ "(:operation is null or e.operation = :operation) and "
			+ "exists (from PlaceDescription pd where (:environments is null or pd.environments = :environments) and pd.id = e.placeDescription)")
	List<Estate> findEstates(
			@Param("environments") Integer environments, 
			@Param("estateType") EstateType estateType, 
			@Param("operation") EstateOperations operation, 
			@Param("price") Double price);

}
