package com.challenge.vaccine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.vaccine.entities.Vaccine;

@Repository
public interface VaccineRespository extends JpaRepository<Vaccine, Long> {

}
