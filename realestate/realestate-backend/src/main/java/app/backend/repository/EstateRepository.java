package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long>, JpaSpecificationExecutor<Estate>{

}
