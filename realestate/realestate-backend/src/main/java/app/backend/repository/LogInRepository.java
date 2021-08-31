package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.LogIn;

public interface LogInRepository extends JpaRepository<LogIn, Long>, JpaSpecificationExecutor<LogIn>{

}
