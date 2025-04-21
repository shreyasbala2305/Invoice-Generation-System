package com.assignment.invoice_generation_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.invoice_generation_system.model.TaxGroup;

@Repository
public interface TaxGroupRepository extends JpaRepository<TaxGroup, Long>{
	Optional<TaxGroup> findByName(String name);
}
