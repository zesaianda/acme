package com.acme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acme.entity.Simulation;

@Repository
public interface SimulationRepository extends CrudRepository<Simulation, Long> {

}
