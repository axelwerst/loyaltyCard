package academy.codilas.loyaltycard.util.mapper;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.repository.entity.CustomerEmtity;
import academy.codilas.loyaltycard.service.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());

        return customerDTO;
    }

    @Override
    public Customer toDomain(CustomerEmtity customerEmtity) {
    Customer customer = new Customer();
    customer.setId(customerEmtity.getId().toString());
    customer.setName(customerEmtity.getName());
    customer.setPhone(customerEmtity.getPhone());
    customer.setEmail(customerEmtity.getEmail());

        return customer;
    }
}