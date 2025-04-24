package com.example.miniapp.controllers;

import com.example.miniapp.models.Customer;
import com.example.miniapp.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return this.customerService.addCustomer(customer);
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Customer customer = this.customerService.getCustomerById(id);
        if (customer == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        return customer;
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (this.customerService.getCustomerById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        return this.customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (this.customerService.getCustomerById(id) == null)
            return "Customer not found";
        this.customerService.deleteCustomer(id);
        return "Customer deleted successfully";
    }

    @GetMapping("/findByEmailDomain")
    public List<Customer> findCustomersByEmailDomain(@RequestParam String domain) {
        return this.customerService.findCustomersByEmailDomain(domain);
    }

    @GetMapping("/findByPhonePrefix")
    public List<Customer> findCustomersByPhonePrefix(@RequestParam String prefix) {
        return this.customerService.findCustomersByPhonePrefix(prefix);
    }

}
