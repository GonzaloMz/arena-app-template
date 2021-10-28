package app.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.backend.model.Estate;
import app.backend.model.enums.EstateOperations;
import app.backend.model.enums.EstateType;
import app.backend.model.enums.Numeration;

public interface EstateRepository extends JpaRepository<Estate, Long>, JpaSpecificationExecutor<Estate>{

	@Query("select e from Estate e "
			+ "where (e.estateType = :estateType or :estateType is null) and "
			+ "(e.price <= :price or :price is null or :price = 0) and "
//			+ "(e.price < :maxprice or :maxprice is null) and "
//			+ "(e.price > :minprice or :minprice is null) and "
			+ "(:operation is null or e.operation = :operation) and "
			+ "exists (from PlaceDescription pd where (:environments is null or pd.environments = :environments) and pd.id = e.placeDescription) "
			+ "and exists (from TemporaryRentFacilities trf where (:numberOfOcupants is null or trf.numberOfOcupants >= :numberOfOcupants) and trf.id = e.temporaryRentFacilities) "
			+ "and not exists (from Rent r where r.estate = e.id and r.checkInDate between :checkInDate and :checkOutDate or r.checkOutDate between :checkInDate and :checkOutDate or ("
			+ "		r.checkInDate < :checkInDate and r.checkOutDate > :checkOutDate)) ")
	List<Estate> findEstates(
			@Param("environments") Integer environments, 
			@Param("estateType") EstateType estateType, 
			@Param("operation") EstateOperations operation, 
			@Param("price") Double price,
			@Param("numberOfOcupants") Numeration numberOfOcupants,
			@Param("checkInDate") Date checkIn,
			@Param("checkOutDate") Date checkOut,
			Sort sort);

}
