package com.assignment.invoice_generation_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.invoice_generation_system.exception.ResourceNotFoundException;
import com.assignment.invoice_generation_system.model.Tax;
import com.assignment.invoice_generation_system.model.TaxGroup;
import com.assignment.invoice_generation_system.repositories.TaxGroupRepository;

@Service
public class TaxGroupService {
    private final TaxGroupRepository taxGroupRepository;
    private final TaxService taxService;
    
    @Autowired
    public TaxGroupService(TaxGroupRepository taxGroupRepository, TaxService taxService) {
        this.taxGroupRepository = taxGroupRepository;
        this.taxService = taxService;
    }
    
    public List<TaxGroup> getAllTaxGroups() {
        return taxGroupRepository.findAll();
    }
    
    public TaxGroup getTaxGroupById(Long id) {
        return taxGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tax Group not found with id: " + id));
    }
    
    public TaxGroup getTaxGroupByName(String name) {
        return taxGroupRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Tax Group not found with name: " + name));
    }
    
    public TaxGroup createTaxGroup(TaxGroup taxGroup) {
        return taxGroupRepository.save(taxGroup);
    }
    
    public TaxGroup addTaxToGroup(Long groupId, Long taxId) {
        TaxGroup taxGroup = getTaxGroupById(groupId);
        Tax tax = taxService.getTaxById(taxId);
        taxGroup.addTax(tax);
        return taxGroupRepository.save(taxGroup);
    }
    
    public TaxGroup updateTaxGroup(Long id, TaxGroup taxGroupDetails) {
        TaxGroup taxGroup = getTaxGroupById(id);
        taxGroup.setName(taxGroupDetails.getName());
        return taxGroupRepository.save(taxGroup);
    }
    
    public void deleteTaxGroup(Long id) {
        TaxGroup taxGroup = getTaxGroupById(id);
        taxGroupRepository.delete(taxGroup);
    }
}