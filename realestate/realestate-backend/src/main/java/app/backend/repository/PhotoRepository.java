package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>, JpaSpecificationExecutor<Photo>{

}
