package com.example.miniapp.services;

import com.example.miniapp.models.Customer;
import com.example.miniapp.models.Payment;
import com.example.miniapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        if (customer != null)
            customer.setId(null);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        if (customer != null)
            customer.setId(id);
        return this.customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> findCustomersByEmailDomain(String domain) {
        return this.customerRepository.findByEmailEndingWithIgnoreCase(domain);
    }

    public List<Customer> findCustomersByPhonePrefix(String prefix) {
        return this.customerRepository.findByPhoneNumberStartingWith(prefix);
    }

}
