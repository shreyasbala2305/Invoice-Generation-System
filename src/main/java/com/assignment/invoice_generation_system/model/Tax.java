package com.assignment.invoice_generation_system.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxes")
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private double percentage;
    
    @JsonBackReference
    @OneToMany(mappedBy = "tax")
    private Set<TaxGroupTax> taxGroups = new HashSet<>();
    
    public Tax() {}
    
    public Tax(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPercentage() {
        return percentage;
    }
    
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    public Set<TaxGroupTax> getTaxGroups() {
        return taxGroups;
    }
    
    public void setTaxGroups(Set<TaxGroupTax> taxGroups) {
        this.taxGroups = taxGroups;
    }
}
