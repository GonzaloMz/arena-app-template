package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>, JpaSpecificationExecutor<Administrator>{

}
