package com.assignment.invoice_generation_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.invoice_generation_system.exception.ResourceNotFoundException;
import com.assignment.invoice_generation_system.model.Tax;
import com.assignment.invoice_generation_system.repositories.TaxRepository;

@Service
public class TaxService {
    private final TaxRepository taxRepository;
    
    @Autowired
    public TaxService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }
    
    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }
    
    public Tax getTaxById(Long id) {
        return taxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tax not found with id: " + id));
    }
    
    public Tax getTaxByName(String name) {
        return taxRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Tax not found with name: " + name));
    }
    
    public Tax createTax(Tax tax) {
        return taxRepository.save(tax);
    }
    
    public Tax updateTax(Long id, Tax taxDetails) {
        Tax tax = getTaxById(id);
        tax.setName(taxDetails.getName());
        tax.setPercentage(taxDetails.getPercentage());
        return taxRepository.save(tax);
    }
    
    public void deleteTax(Long id) {
        Tax tax = getTaxById(id);
        taxRepository.delete(tax);
    }
}