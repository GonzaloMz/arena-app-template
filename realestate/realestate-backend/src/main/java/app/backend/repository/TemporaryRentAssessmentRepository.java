package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.TemporaryRentAssessment;

public interface TemporaryRentAssessmentRepository extends JpaRepository<TemporaryRentAssessment, Long>, JpaSpecificationExecutor<TemporaryRentAssessment>{

}
