package com.accenture.store.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.store.backend.dtos.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	


}
