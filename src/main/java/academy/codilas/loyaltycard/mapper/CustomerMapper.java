package academy.codilas.loyaltycard.mapper;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import academy.codilas.loyaltycard.service.domain.Customer;

public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toDomain(CustomerEntity customerEmtity);

}
