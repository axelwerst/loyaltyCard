package academy.codilas.loyaltycard.domain.service;


import java.util.*;

import academy.codilas.loyaltycard.domain.model.Customer;
import academy.codilas.loyaltycard.domain.repository.CustomerRepository;
import academy.codilas.loyaltycard.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        return customerRepository.save(customer);
    }

    @Override
    public Customer newCustomer(String email, String phone, String name) {
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {

    }

    @Override
    public Customer updateCustomer(String name, String email, String phone, String customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        return customer.orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Customer updateCustomer(UUID customerId, Customer customerToUpdateFrom) {
        Customer customer = getCustomerById(customerId);

        customer.update(customerToUpdateFrom);

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        Customer customer = getCustomerById(customerId);

        customerRepository.delete(customer);
    }
}