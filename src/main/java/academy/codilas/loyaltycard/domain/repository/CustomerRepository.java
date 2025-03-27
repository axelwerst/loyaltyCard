package academy.codilas.loyaltycard.domain.repository;


import academy.codilas.loyaltycard.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(UUID customerId);

    void delete(Customer customer);
}
