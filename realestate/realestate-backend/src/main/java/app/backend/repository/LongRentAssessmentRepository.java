package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.LongRentAssessment;

public interface LongRentAssessmentRepository extends JpaRepository<LongRentAssessment, Long>, JpaSpecificationExecutor<LongRentAssessment>{

}
