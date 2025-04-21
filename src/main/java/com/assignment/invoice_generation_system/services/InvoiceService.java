package com.assignment.invoice_generation_system.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.invoice_generation_system.dto.InvoiceDTO;
import com.assignment.invoice_generation_system.dto.InvoiceItemDTO;
import com.assignment.invoice_generation_system.exception.ResourceNotFoundException;
import com.assignment.invoice_generation_system.model.Invoice;
import com.assignment.invoice_generation_system.model.InvoiceItem;
import com.assignment.invoice_generation_system.model.Product;
import com.assignment.invoice_generation_system.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;
    
    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ProductService productService) {
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
    }
    
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }
    
    public Invoice getInvoiceByNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with number: " + invoiceNumber));
    }
    
    @Transactional
    public Invoice createInvoice(String customerName, String customerEmail, List<InvoiceItemDTO> items) {
        Invoice invoice = new Invoice(customerName, customerEmail);
        
        for (InvoiceItemDTO itemDTO : items) {
            Product product = productService.getProductById(itemDTO.getProductId());
            InvoiceItem item = new InvoiceItem(product, itemDTO.getQuantity());
            invoice.addItem(item);
        }
        
        return invoiceRepository.save(invoice);
    }
    
    @Transactional
    public Invoice addItemToInvoice(Long invoiceId, Long productId, int quantity) {
        Invoice invoice = getInvoiceById(invoiceId);
        Product product = productService.getProductById(productId);
        
        InvoiceItem item = new InvoiceItem(product, quantity);
        invoice.addItem(item);
        
        return invoiceRepository.save(invoice);
    }
    
    public InvoiceDTO convertToDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDTO.setCustomerName(invoice.getCustomerName());
        invoiceDTO.setCustomerEmail(invoice.getCustomerEmail());
        invoiceDTO.setCreatedAt(invoice.getCreatedAt());
        
        List<InvoiceItemDTO> itemDTOs = invoice.getItems().stream()
                .map(item -> {
                    InvoiceItemDTO itemDTO = new InvoiceItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setProductId(item.getProduct().getId());
                    itemDTO.setProductName(item.getProduct().getName());
                    itemDTO.setProductPrice(item.getProduct().getPrice());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setTaxAmount(item.getTotalTax());
                    itemDTO.setTotalPrice(item.getTotalPrice());
                    return itemDTO;
                })
                .collect(Collectors.toList());
        
        invoiceDTO.setItems(itemDTOs);
        invoiceDTO.setSubtotal(invoice.calculateSubtotal());
        invoiceDTO.setTotalTax(invoice.calculateTotalTax());
        invoiceDTO.setGrandTotal(invoice.calculateGrandTotal());
        
        return invoiceDTO;
    }
    
    public void deleteInvoice(Long id) {
        Invoice invoice = getInvoiceById(id);
        invoiceRepository.delete(invoice);
    }
}
