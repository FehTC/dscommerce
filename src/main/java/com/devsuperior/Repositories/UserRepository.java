package com.devsuperior.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.Entitie.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


	User findByEmail(String username);
	
	
}