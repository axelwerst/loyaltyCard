package academy.codilas.loyaltycard.util.mapper;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.repository.entity.CustomerEmtity;
import academy.codilas.loyaltycard.service.domain.Customer;

public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

    Customer toDomain(CustomerEmtity customerEmtity);
}