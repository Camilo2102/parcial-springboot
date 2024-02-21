package com.example.parcial.service;

import com.example.parcial.exceptions.HttpException;
import com.example.parcial.models.CustomerModel;
import com.example.parcial.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers() throws HttpException {
        try {
            return customerRepository.findAll();
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not get the customers");
        }
    }

    public CustomerModel getCustomer(String id) throws HttpException {
        return this.getCustomerById(id);
    }

    public CustomerModel createCustomer(CustomerModel customer) throws HttpException {
        try {
            return this.customerRepository.save(customer);
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not get the customers");
        }
    }

    public CustomerModel deleteCustomer(String id) throws HttpException {
        CustomerModel customer = this.getCustomerById(id);
        try {
            customerRepository.deleteById(id);
            return customer;
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete customer");
        }
    }

    private CustomerModel getCustomerById(String id) throws HttpException {
        Optional<CustomerModel> customerFound = customerRepository.findById(id);

        if(customerFound.isEmpty()) throw new HttpException(HttpStatus.NOT_FOUND, "Customer not found");

        return customerFound.get();
    }

}
