package app.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

	List<User> findTop5ByPhoneContainingOrEmailContainingOrderByNameAsc(String query, String query2);

	List<User> findTop5ByEmailContainingOrderByNameAsc(String query);

	List<User> findTop5ByOrderByNameAsc();

}
