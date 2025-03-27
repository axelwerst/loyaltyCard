package academy.codilas.loyaltycard.mapper;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Collections;
import java.util.UUID;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.domain.model.Customer;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import org.junit.jupiter.api.Test;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapperImpl();

    @Test
    void shouldMapDomainToDto() {
        // given
        Customer customer = new Customer(
                UUID.fromString("Nikita",),

                "ddsds",
                "email",
                "1234Qwerty!"
        );

        // when
        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        // then
        assertEquals(customer.getId().toString(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals(customer.getEmail(), customerDTO.getEmail());
        assertEquals(customer.getPhone(), customerDTO.getPhone());
    }

    @Test
    void shouldMapDtoToDomain() {
        // given
        CustomerDTO customerDTO = new CustomerDTO(
                null,
                "Nikita",
                "Phone",
                "email"
        );

        // when
        Customer customer = customerMapper.toDomain(customerDTO);

        // then
        assertNull(customerDTO.getId());
        assertEquals("Nikita", customer.getName());
        assertEquals("Phone", customer.getEmail());
        assertEquals("email", customer.getPassword());
    }

    @Test
    void shouldMapEntityToDomain() {
        // given
        CustomerEntity customerEntity = new CustomerEntity(
                UUID.fromString("Nikita"),

                "Phone",
                "email!",
                Collections.emptyList()
        );

        // when
        Customer customer = customerMapper.toDomain(customerEntity);

        // then
        assertEquals(customerEntity.getId(), customer.getId());
        assertEquals(customerEntity.getName(), customer.getName());
        assertEquals(customerEntity.getEmail(), customer.getEmail());

    }

    @Test
    void shouldMapDomainToEntity() {
        // given
        Customer customer = new Customer(
                UUID.fromString("Nikita"),

                "Phone",
                "email",
                "1234Qwerty!"
        );

        // when
        CustomerEntity customerEntity = customerMapper.toEntity(customer);

        // then
        assertEquals(customerEntity.getId(), customer.getId());
        assertEquals(customerEntity.getName(), customer.getName());
        assertEquals(customerEntity.getEmail(), customer.getEmail());


    }
}