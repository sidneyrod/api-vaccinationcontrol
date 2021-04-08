package com.challenge.vaccine.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.vaccine.entities.Users;
import com.challenge.vaccine.entities.Vaccine;

@Repository
public interface UsersRespository extends JpaRepository<Users, Long> {

	@Query("SELECT DISTINCT obj FROM Users obj INNER JOIN obj.vaccines vacc WHERE "
			+ "(COALESCE(:vaccines) IS NULL OR :vaccines IN :vaccines) AND "
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%')))")
	Page<Users> find(List<Vaccine> vaccines, String name, Pageable pageable);
	
	@Query("SELECT obj FROM Users obj JOIN FETCH obj.vaccines WHERE obj IN :users")
	List<Users> findUsersWithVaccines(List<Users> users);
}
