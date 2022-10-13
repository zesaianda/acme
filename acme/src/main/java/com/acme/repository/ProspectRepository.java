package com.acme.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.entity.Prospect;

@Repository
public interface ProspectRepository extends CrudRepository<Prospect, Long> {

	@Query("SELECT p FROM Prospect p WHERE p.email = :email")
	Prospect findByEmail(@Param("email") String email);

}
