package academy.codilas.LoyaltyCard.controller;

import academy.codilas.LoyaltyCard.controller.DTO.CustomerDTO;
import academy.codilas.LoyaltyCard.controller.DTO.NewCustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private Map<String, CustomerDTO> customersMap = new HashMap();

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customersMap
                .values()
                .forEach(customerDTO -> customerDTOList.add(customerDTO));
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(customerDTOList);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
        CustomerDTO customerDTO = customersMap.get(customerId);
        if (customerDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        if (customersMap.containsKey(customerId)) {
            customersMap.remove(customerId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> NewCustomer(@RequestBody NewCustomerDTO newCustomerDTO) {
        String id = UUID.randomUUID().toString();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setName(newCustomerDTO.getName());
        customerDTO.setPhone(newCustomerDTO.getPhone());
        customerDTO.setEmail(newCustomerDTO.getEmail());

        customersMap.put(id, customerDTO);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(customerDTO);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String customerId, @RequestBody NewCustomerDTO updatedCustomerDTO) {
        if (!customersMap.containsKey(customerId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CustomerDTO existingCustomer = customersMap.get(customerId);
        existingCustomer.setName(updatedCustomerDTO.getName());
        existingCustomer.setPhone(updatedCustomerDTO.getPhone());
        existingCustomer.setEmail(updatedCustomerDTO.getEmail());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingCustomer);
    }

}

