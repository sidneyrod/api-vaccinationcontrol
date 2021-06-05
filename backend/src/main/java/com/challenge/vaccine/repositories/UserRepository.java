package com.challenge.vaccine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.vaccine.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
