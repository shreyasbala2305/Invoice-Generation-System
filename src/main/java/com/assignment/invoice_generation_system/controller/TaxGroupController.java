package com.assignment.invoice_generation_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.invoice_generation_system.model.TaxGroup;
import com.assignment.invoice_generation_system.services.TaxGroupService;

@RestController
@RequestMapping("/api/tax-groups")
public class TaxGroupController {
    private final TaxGroupService taxGroupService;
    
    @Autowired
    public TaxGroupController(TaxGroupService taxGroupService) {
        this.taxGroupService = taxGroupService;
    }
    
    @GetMapping
    public List<TaxGroup> getAllTaxGroups() {
        return taxGroupService.getAllTaxGroups();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaxGroup> getTaxGroupById(@PathVariable Long id) {
        TaxGroup taxGroup = taxGroupService.getTaxGroupById(id);
        return ResponseEntity.ok(taxGroup);
    }
    
    @PostMapping
    public ResponseEntity<TaxGroup> createTaxGroup(@RequestBody TaxGroup taxGroup) {
        TaxGroup newTaxGroup = taxGroupService.createTaxGroup(taxGroup);
        return ResponseEntity.ok(newTaxGroup);
    }
    
    @PostMapping("/{groupId}/taxes/{taxId}")
    public ResponseEntity<TaxGroup> addTaxToGroup(
            @PathVariable Long groupId,
            @PathVariable Long taxId) {
        TaxGroup updatedTaxGroup = taxGroupService.addTaxToGroup(groupId, taxId);
        return ResponseEntity.ok(updatedTaxGroup);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaxGroup> updateTaxGroup(@PathVariable Long id, @RequestBody TaxGroup taxGroupDetails) {
        TaxGroup updatedTaxGroup = taxGroupService.updateTaxGroup(id, taxGroupDetails);
        return ResponseEntity.ok(updatedTaxGroup);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaxGroup(@PathVariable Long id) {
        taxGroupService.deleteTaxGroup(id);
        return ResponseEntity.ok().build();
    }
}