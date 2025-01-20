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

    private Map<String,CustomerDTO> customersMap = new HashMap();

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getPerson() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customersMap
                .values()
                .forEach(CustomerDTO -> customerDTOList.add(CustomerDTO));
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(customerDTOList);
    }
        @PostMapping
        public ResponseEntity<CustomerDTO> NewCustomer(@RequestBody NewCustomerDTO newCustomerDTO) {
        String id = UUID.randomUUID().toString();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setName(newCustomerDTO.getName());
        customerDTO.setPhone(newCustomerDTO.getPhone());
        customerDTO.setEmail(newCustomerDTO.getEmail());

        customersMap.put(id,customerDTO);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(customerDTO);
        }

}
