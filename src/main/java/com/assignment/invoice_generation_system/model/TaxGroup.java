package com.assignment.invoice_generation_system.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_groups")
public class TaxGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @JsonBackReference
    @OneToMany(mappedBy = "taxGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaxGroupTax> taxes = new HashSet<>();
    
    @JsonBackReference
    @OneToMany(mappedBy = "taxGroup")
    private Set<Product> products = new HashSet<>();
        
    public TaxGroup() {}
    
    public TaxGroup(String name) {
        this.name = name;
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
    
    public Set<TaxGroupTax> getTaxes() {
        return taxes;
    }
    
    public void setTaxes(Set<TaxGroupTax> taxes) {
        this.taxes = taxes;
    }
    
    public Set<Product> getProducts() {
        return products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
      
    public void addTax(Tax tax) {
        TaxGroupTax taxGroupTax = new TaxGroupTax(this, tax);
        taxes.add(taxGroupTax);
    }
    
    public double calculateTotalTaxPercentage() {
        return taxes.stream()
                .mapToDouble(taxGroupTax -> taxGroupTax.getTax().getPercentage())
                .sum();
    }
}
