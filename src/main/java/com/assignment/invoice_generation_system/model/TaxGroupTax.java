package com.assignment.invoice_generation_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_group_taxes")
public class TaxGroupTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "tax_group_id")
    private TaxGroup taxGroup;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "tax_id")
    private Tax tax;
    

    public TaxGroupTax() {}
    
    public TaxGroupTax(TaxGroup taxGroup, Tax tax) {
        this.taxGroup = taxGroup;
        this.tax = tax;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public TaxGroup getTaxGroup() {
        return taxGroup;
    }
    
    public void setTaxGroup(TaxGroup taxGroup) {
        this.taxGroup = taxGroup;
    }
    
    public Tax getTax() {
        return tax;
    }
    
    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
