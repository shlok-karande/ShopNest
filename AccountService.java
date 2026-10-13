package com.shopnest.service;

import com.shopnest.model.Customer;
import com.shopnest.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final CustomerRepository customers;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public AccountService(CustomerRepository customers){this.customers=customers;}
    public Customer register(String name, String email, String password) {
        String cleanEmail=email == null ? "" : email.trim().toLowerCase();
        if(name == null || name.trim().length()<2) throw new IllegalArgumentException("Please enter your full name.");
        if(cleanEmail.isBlank() || !cleanEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) throw new IllegalArgumentException("Please enter a valid email.");
        if(password == null || password.length()<8) throw new IllegalArgumentException("Password must be at least 8 characters.");
        if(customers.existsByEmailIgnoreCase(cleanEmail)) throw new IllegalArgumentException("An account with this email already exists.");
        return customers.save(new Customer(name.trim(),cleanEmail,encoder.encode(password)));
    }
    public Customer login(String email, String password) {
        Customer c=customers.findByEmailIgnoreCase(email == null ? "" : email.trim())
            .orElseThrow(()->new IllegalArgumentException("Email or password is incorrect."));
        if(password == null || !encoder.matches(password,c.getPasswordHash()))
            throw new IllegalArgumentException("Email or password is incorrect.");
        return c;
    }
}
