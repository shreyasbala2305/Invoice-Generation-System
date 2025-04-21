package com.assignment.invoice_generation_system.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private double price;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "tax_group_id")
    private TaxGroup taxGroup;
    
    @JsonBackReference
    @OneToMany(mappedBy = "product")
    private Set<InvoiceItem> invoiceItems = new HashSet<>();
    
    public Product() {}
    
    public Product(String name, double price, TaxGroup taxGroup) {
        this.name = name;
        this.price = price;
        this.taxGroup = taxGroup;
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
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public TaxGroup getTaxGroup() {
        return taxGroup;
    }
    
    public void setTaxGroup(TaxGroup taxGroup) {
        this.taxGroup = taxGroup;
    }
    
    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
    

    public double calculateTaxAmount() {
        return price * (taxGroup.calculateTotalTaxPercentage() / 100);
    }
}
