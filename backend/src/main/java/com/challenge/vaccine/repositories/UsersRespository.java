package com.challenge.vaccine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.vaccine.entities.Users;

@Repository
public interface UsersRespository extends JpaRepository<Users, Long> {

	
}
