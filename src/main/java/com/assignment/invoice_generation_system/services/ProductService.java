package com.assignment.invoice_generation_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.invoice_generation_system.exception.ResourceNotFoundException;
import com.assignment.invoice_generation_system.model.Product;
import com.assignment.invoice_generation_system.model.TaxGroup;
import com.assignment.invoice_generation_system.repositories.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final TaxGroupService taxGroupService;
    
    @Autowired
    public ProductService(ProductRepository productRepository, TaxGroupService taxGroupService) {
        this.productRepository = productRepository;
        this.taxGroupService = taxGroupService;
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
    
    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));
    }
    
    public Product createProduct(Product product, Long taxGroupId) {
        TaxGroup taxGroup = taxGroupService.getTaxGroupById(taxGroupId);
        product.setTaxGroup(taxGroup);
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails, Long taxGroupId) {
        Product product = getProductById(id);
        TaxGroup taxGroup = taxGroupService.getTaxGroupById(taxGroupId);
        
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setTaxGroup(taxGroup);
        
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}