package com.assignment.invoice_generation_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.invoice_generation_system.model.TaxGroupTax;

@Repository
public interface TaxGroupTaxRepository extends JpaRepository<TaxGroupTax, Long> {
}