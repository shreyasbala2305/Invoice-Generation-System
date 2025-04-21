package com.assignment.invoice_generation_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.invoice_generation_system.model.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long>{
	Optional<Tax> findByName(String name);
}
