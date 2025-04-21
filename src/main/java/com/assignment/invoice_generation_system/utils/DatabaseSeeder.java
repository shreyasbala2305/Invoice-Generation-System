package com.assignment.invoice_generation_system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.assignment.invoice_generation_system.model.Product;
import com.assignment.invoice_generation_system.model.Tax;
import com.assignment.invoice_generation_system.model.TaxGroup;
import com.assignment.invoice_generation_system.repositories.ProductRepository;
import com.assignment.invoice_generation_system.repositories.TaxGroupRepository;
import com.assignment.invoice_generation_system.repositories.TaxRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final TaxRepository taxRepository;
    private final TaxGroupRepository taxGroupRepository;
    private final ProductRepository productRepository;
    
    @Autowired
    public DatabaseSeeder(
            TaxRepository taxRepository,
            TaxGroupRepository taxGroupRepository,
            ProductRepository productRepository) {
        this.taxRepository = taxRepository;
        this.taxGroupRepository = taxGroupRepository;
        this.productRepository = productRepository;
    }
    
    @Override
    public void run(String... args) {
        if (taxRepository.count() > 0) {
            return;
        }
        
        Tax noTax = taxRepository.save(new Tax("No Tax", 0));
        Tax vat = taxRepository.save(new Tax("VAT", 1.5));
        Tax sgst = taxRepository.save(new Tax("SGST", 1.0));
        Tax cgst = taxRepository.save(new Tax("CGST", 1.0));
        
        TaxGroup noTaxGroup = new TaxGroup("No Tax");
        noTaxGroup.addTax(noTax);
        taxGroupRepository.save(noTaxGroup);
        
        TaxGroup vatGroup = new TaxGroup("VAT Only");
        vatGroup.addTax(vat);
        taxGroupRepository.save(vatGroup);
        
        TaxGroup mixedTaxGroup = new TaxGroup("SGST + CGST");
        mixedTaxGroup.addTax(sgst);
        mixedTaxGroup.addTax(cgst);
        taxGroupRepository.save(mixedTaxGroup);
        
        productRepository.save(new Product("Rice", 20, noTaxGroup));
        productRepository.save(new Product("Petrol", 65, vatGroup));
        productRepository.save(new Product("Soap", 10, mixedTaxGroup));
        productRepository.save(new Product("Wheat", 15, noTaxGroup));
        productRepository.save(new Product("Diesel", 60, vatGroup));
        productRepository.save(new Product("Shampoo", 25, mixedTaxGroup));
    }
}
