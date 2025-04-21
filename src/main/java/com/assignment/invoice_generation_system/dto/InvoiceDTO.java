package com.assignment.invoice_generation_system.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private String invoiceNumber;
    private String customerName;
    private String customerEmail;
    private LocalDateTime createdAt;
    private List<InvoiceItemDTO> items = new ArrayList<>();
    private double subtotal;
    private double totalTax;
    private double grandTotal;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<InvoiceItemDTO> getItems() {
        return items;
    }
    
    public void setItems(List<InvoiceItemDTO> items) {
        this.items = items;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public double getTotalTax() {
        return totalTax;
    }
    
    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }
    
    public double getGrandTotal() {
        return grandTotal;
    }
    
    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
}