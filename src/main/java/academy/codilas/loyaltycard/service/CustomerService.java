package academy.codilas.loyaltycard.service;

import academy.codilas.loyaltycard.exception.CustomerNotFoundExeption;
import academy.codilas.loyaltycard.service.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer newCustomer(String email, String phone, String name);

    List<Customer> getCustomers();

    Customer getCustomerById(String id) throws CustomerNotFoundExeption;

    void deleteCustomer(String id) throws CustomerNotFoundExeption;

    Customer updateCustomer(String name, String email, String phone, String customerId) throws CustomerNotFoundExeption;
}

