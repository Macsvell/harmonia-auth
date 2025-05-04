package com.harmonia.HmAuthService.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harmonia.HmAuthService.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
	User findByLogin(String login);
	boolean existsByLogin(String login);
	User findByEmail(String email);
	boolean existsByEmail(String email);
}
