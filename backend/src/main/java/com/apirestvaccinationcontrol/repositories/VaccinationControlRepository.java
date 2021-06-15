package com.apirestvaccinationcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestvaccinationcontrol.entities.VaccinationControl;

@Repository
public interface VaccinationControlRepository extends JpaRepository<VaccinationControl, Long> {
}
