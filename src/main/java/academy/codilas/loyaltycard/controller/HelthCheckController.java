package academy.codilas.loyaltycard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelthCheckController {
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("server start and running");
    }
}
