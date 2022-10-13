package com.acme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acme.entity.Coverage;

@Repository
public interface CoverageRepository extends CrudRepository<Coverage, Long> {

	Coverage findByName(String name);

}
