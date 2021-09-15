/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.User;
import app.backend.repository.UserRepository;
import arena.backend.model.extension.ShapeFactory;
import arena.backend.service.ArenaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author 
 */
@Component
public class UserService extends ArenaService<User,User>{
	
	@Autowired
	UserRepository userRepository;

	@Override
	protected JpaRepository<User, Long> getRepository() {
		return userRepository;
	}

	@Override
	public User create(Optional<User> ent) {
		return userRepository.save(ent.get());
	}

	@Override
	public List<User> searchInLine(String query) {
		if (StringUtils.isEmpty(query)) {
			return new ArrayList<>();
		}
		if (query.contains("@"))
			return userRepository.findTop5ByEmailContainingOrderByNameAsc(query);
		return userRepository.findTop5ByPhoneContainingOrEmailContainingOrNameContainingIgnoreCaseOrderByNameAsc(query, query, query);
	}
	
	@Override
	public
	ShapeFactory getShapeFactory() {
		return new ShapeFactory(User.class);
	}
	
}
