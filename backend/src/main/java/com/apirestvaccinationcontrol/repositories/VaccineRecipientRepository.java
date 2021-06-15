package com.apirestvaccinationcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestvaccinationcontrol.entities.VaccineRecipient;

@Repository
public interface VaccineRecipientRepository extends JpaRepository<VaccineRecipient, Long> {

}
