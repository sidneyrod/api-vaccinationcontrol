package com.challenge.vaccine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.vaccine.entities.VaccinationControl;

@Repository
public interface VaccinationControlRepository extends JpaRepository<VaccinationControl, Long> {
}
