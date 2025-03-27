package academy.codilas.loyaltycard.controller;

import academy.codilas.loyaltycard.controller.DTO.CustomerDTO;
import academy.codilas.loyaltycard.controller.DTO.ErrorDTO;
import academy.codilas.loyaltycard.exception.CustomerNotFoundException;
import academy.codilas.loyaltycard.domain.service.CustomerService;
import academy.codilas.loyaltycard.domain.model.Customer;
import academy.codilas.loyaltycard.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    //
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        List<Customer> customerList = customerService.getCustomers();
        List<CustomerDTO> customerDTOList = customerList
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTOList);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) throws CustomerNotFoundException {

        Customer customer = customerService.getCustomerById(customerId);

        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(customerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> NewCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.newCustomer(customerDTO.getEmail(), customerDTO.getPhone(), customerDTO.getName());
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(customerDTO);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable String customerId,
            @RequestBody NewCustomerDTO updatedCustomerDTO) throws CustomerNotFoundException {
        Customer updatedCustomer = customerService.updateCustomer(
                updatedCustomerDTO.getName(),
                updatedCustomerDTO.getEmail(),
                updatedCustomerDTO.getPhone(),
                customerId
        );
        CustomerDTO updatedCustomerDTOResponse = customerMapper.toDTO(updatedCustomer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCustomerDTOResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCustomerNotFoundException(CustomerNotFoundException e) {

        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

