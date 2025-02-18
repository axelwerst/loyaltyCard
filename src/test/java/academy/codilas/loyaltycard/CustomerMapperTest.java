package academy.codilas.loyaltycard;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.mapper.CustomerMapperImpl;
import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import academy.codilas.loyaltycard.service.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private CustomerMapperImpl customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapperImpl();
    }

    @Test
    void shouldMapCustomerToDTO() {
        // Arrange

        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(id, "John Doe",  "1234567890","john.doe@example.com");

        // Act
        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        // Assert
        assertThat(customerDTO).isNotNull();
        assertThat(customerDTO.getId()).isEqualTo(id);
        assertThat(customerDTO.getName()).isEqualTo("John Doe");
        assertThat(customerDTO.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(customerDTO.getPhone()).isEqualTo("1234567890");
    }

    @Test
    void shouldMapEntityToDomain() {

        String id = UUID.randomUUID().toString();
        CustomerEntity customerEntity = new CustomerEntity(id, "Jane Doe", "jane.doe@example.com", "0987654321");


        Customer customer = customerMapper.toDomain(customerEntity);


        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getName()).isEqualTo("Jane Doe");
        assertThat(customer.getEmail()).isEqualTo("jane.doe@example.com");
        assertThat(customer.getPhone()).isEqualTo("0987654321");
    }

    @Test
    void shouldReturnNullWhenMappingNullCustomer() {
        // Act
        CustomerDTO customerDTO = customerMapper.toDTO(null);

        // Assert
        assertThat(customerDTO).isNull();
    }

    @Test
    void shouldReturnNullWhenMappingNullEntity() {
        // Act
        Customer customer = customerMapper.toDomain(null);

        // Assert
        assertThat(customer).isNull();
    }
}