package com.example.parcial.controller;

import com.example.parcial.exceptions.HttpException;
import com.example.parcial.models.CustomerModel;
import com.example.parcial.responses.ResponseHandler;
import com.example.parcial.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/")
    public ResponseEntity<?> getCustomerList() throws HttpException {
        List<CustomerModel> customers = customerService.getAllCustomers();
        return ResponseHandler.generateResponse("Customers found", HttpStatus.OK, customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) throws HttpException {
        CustomerModel customer = customerService.getCustomer(id);
        return ResponseHandler.generateResponse("Customer found", HttpStatus.OK, customer);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerModel customer) throws HttpException {
        CustomerModel customerCreated = customerService.createCustomer(customer);
        return ResponseHandler.generateResponse("Customer created", HttpStatus.ACCEPTED, customerCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String id) throws HttpException {
        CustomerModel customer = customerService.deleteCustomer(id);
        return ResponseHandler.generateResponse("Customer deleted", HttpStatus.ACCEPTED, customer);
    }
}
