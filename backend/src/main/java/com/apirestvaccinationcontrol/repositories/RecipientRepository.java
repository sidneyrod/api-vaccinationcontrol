package com.apirestvaccinationcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestvaccinationcontrol.entities.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

}
