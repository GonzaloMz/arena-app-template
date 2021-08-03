package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.EstateSearch;

public interface EstateSearchRepository extends JpaRepository<EstateSearch, Long>, JpaSpecificationExecutor<EstateSearch>{

}
