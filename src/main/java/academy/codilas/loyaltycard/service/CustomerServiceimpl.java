package academy.codilas.loyaltycard.service;


import academy.codilas.loyaltycard.exception.CustomerNotFoundExeption;
import academy.codilas.loyaltycard.repository.CustomerRepository;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import academy.codilas.loyaltycard.service.domain.Customer;
import academy.codilas.loyaltycard.mapper.CustomerMapper;
import academy.codilas.loyaltycard.mapper.CustomerMapperImpl;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerServiceimpl implements CustomerService {

    private Map<String, Customer> customersMap;
    private final CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    public CustomerServiceimpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customersMap = new HashMap<>();
        this.customerMapper = new CustomerMapperImpl();
    }


    @Override
    public Customer newCustomer(String name, String email, String phone) {

        String id = UUID.randomUUID().toString();

        Customer customer = new Customer(id, "John Doe", "john.doe@example.com", "1234567890");
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);

        return customersMap.put(id, customer);
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundExeption {
        if (customersMap.containsKey(id)) {
            customersMap.remove(id);
        }
        throw new CustomerNotFoundExeption("Клиент не найден ");
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customersMap
                .values()
                .forEach(customer -> customerList.add(customer));
        return customerList;
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerNotFoundExeption {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(UUID.fromString(id));
        if (optionalCustomerEntity.isPresent()) {
            return customerMapper.toDomain(optionalCustomerEntity.get());
        }


        throw new CustomerNotFoundExeption("Клиент с айди " + id + " ненайден");
    }


    @Override
    public Customer updateCustomer(String name, String email, String phone, String customerId) throws CustomerNotFoundExeption {

        Customer existingCustomer = customersMap.get(customerId);
        if (existingCustomer == null) {
            throw new CustomerNotFoundExeption("Клиент с ID " + customerId + " не найден");
        }

        existingCustomer.setName(name);
        existingCustomer.setEmail(email);
        existingCustomer.setPhone(phone);
        customersMap.put(customerId, existingCustomer);
        return existingCustomer;
    }
}

