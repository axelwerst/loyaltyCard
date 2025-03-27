package academy.codilas.loyaltycard.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import academy.codilas.loyaltycard.domain.model.Customer;
import academy.codilas.loyaltycard.domain.repository.CustomerRepository;
import academy.codilas.loyaltycard.mapper.CustomerMapper;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerMapper customerMapper;
    private final CustomerJpaRepository customerJpaRepository;

    @Autowired
    public CustomerRepositoryImpl(CustomerMapper customerMapper, CustomerJpaRepository customerJpaRepository) {
        this.customerMapper = customerMapper;
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        CustomerEntity savedCustomerEntity = customerJpaRepository.save(customerEntity);
        return customerMapper.toDomain(savedCustomerEntity);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream().map(customerMapper::toDomain).toList();
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerMapper::toDomain);
    }

    @Override
    public void delete(Customer customer) {
        customerJpaRepository.deleteById(customer.getId());
    }
}