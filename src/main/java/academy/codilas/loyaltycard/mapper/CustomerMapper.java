package academy.codilas.loyaltycard.mapper;


import academy.codilas.loyaltycard.domain.model.Customer;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toDomain(CustomerDTO customerDTO);

    Customer toDomain(CustomerEntity customerEntity);

    CustomerEntity toEntity(Customer customer);
}
