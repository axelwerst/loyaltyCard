package academy.codilas.loyaltycard.domain.service;

import academy.codilas.loyaltycard.exception.CustomerNotFoundException;
import academy.codilas.loyaltycard.domain.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer newCustomer(String email, String phone, String name);

    List<Customer> getCustomers();

    Customer getCustomerById(String id) throws CustomerNotFoundException;

    void deleteCustomer(String id) throws CustomerNotFoundException;

    Customer updateCustomer(String name, String email, String phone, String customerId) throws CustomerNotFoundException;
}

