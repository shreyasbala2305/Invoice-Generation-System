package com.assignment.invoice_generation_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.invoice_generation_system.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
}
