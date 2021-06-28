package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long>, JpaSpecificationExecutor<Assessment>{

}
