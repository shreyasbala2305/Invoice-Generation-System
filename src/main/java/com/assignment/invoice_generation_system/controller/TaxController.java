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

import com.assignment.invoice_generation_system.model.Tax;
import com.assignment.invoice_generation_system.services.TaxService;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
    private final TaxService taxService;
    
    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }
    
    @GetMapping
    public List<Tax> getAllTaxes() {
        return taxService.getAllTaxes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTaxById(@PathVariable Long id) {
        Tax tax = taxService.getTaxById(id);
        return ResponseEntity.ok(tax);
    }
    
    @PostMapping
    public ResponseEntity<Tax> createTax(@RequestBody Tax tax) {
        Tax newTax = taxService.createTax(tax);
        return ResponseEntity.ok(newTax);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long id, @RequestBody Tax taxDetails) {
        Tax updatedTax = taxService.updateTax(id, taxDetails);
        return ResponseEntity.ok(updatedTax);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        return ResponseEntity.ok().build();
    }
}