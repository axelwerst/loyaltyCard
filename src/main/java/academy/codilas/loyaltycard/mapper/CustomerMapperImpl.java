package academy.codilas.loyaltycard.mapper;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import academy.codilas.loyaltycard.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDTO toDTO(Customer customer) {
        return null;
    }

    @Override
    public Customer toDomain(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public Customer toDomain(CustomerEntity customerEntity) {
        return null;
    }

    @Override
    public CustomerEntity toEntity(Customer customer) {
        return null;
    }

//    @Override
//    public CustomerDTO toDTO(Customer customer) {
//        if (customer == null) {
//            return null;
//        }
//
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setPhone(customer.getPhone());
//        customerDTO.setEmail(customer.getEmail());
//
//        return customerDTO;
//    }
//
//    @Override
//    public Customer toDomain(CustomerEntity customerEntity) {
//
//            if (customerEntity == null) {
//                return null;
//            }
////
//
//    Customer customer = new Customer(String.valueOf(id), "John Doe", "john.doe@example.com", "1234567890");
//    customer.setId(UUID.fromString(customerEntity.getId().toString()));
//    customer.setName(customerEntity.getName());
//    customer.setPhone(customerEntity.getPhone());
//    customer.setEmail(customerEntity.getEmail());
//
//        return customer;
//    }
//
//    @Override
//    public CustomerEntity toEntity(Customer customer) {
//        return null;
//    }
}