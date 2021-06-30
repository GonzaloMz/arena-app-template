/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.backend.service;

import app.backend.model.User;
import app.backend.repository.UserRepository;
import arena.backend.service.ArenaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

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
		if (query.contains("@"))
			return userRepository.findByEmailLike(query);
		return userRepository.findByPhoneLikeOrEmailLike(query, query);
	}
	
}
