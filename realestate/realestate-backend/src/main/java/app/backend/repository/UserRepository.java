package app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

}
