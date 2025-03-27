package academy.codilas.loyaltycard.controller;

import static org.junit.jupiter.api.Assertions.*;


import academy.codilas.loyaltycard.controller.DTO.AdminDTO;
import academy.codilas.loyaltycard.controller.DTO.AuthTokenDTO;
import academy.codilas.loyaltycard.controller.DTO.ErrorDTO;
import academy.codilas.loyaltycard.controller.DTO.LoginDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    void setUpTestData() {
        restTemplate.postForEntity(
                getBaseUrl() + "/sign-up",
                new HttpEntity<>(new AdminDTO(null, "Nikita", "987654321", "test@mail.com")),
                Void.class);
    }

    @Test
    void shouldSignUpSuccessfully() {
        // given: create a AdminDto object representing a new user
        AdminDTO adminDTO =
                new AdminDTO(null, "James", "123456789", "correct_email@email.com");

        // when: send a POST request to /sign-up
        ResponseEntity<Void> response =
                restTemplate.postForEntity(
                        getBaseUrl() + "/sign-up", new HttpEntity<>(adminDTO), Void.class);

        // then: expect a CREATED status code (201)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldLoginSuccessfully() {
        // given: prepare login credentials (ensure that a corresponding user exists in the test DB)
        LoginDTO loginDTO = new LoginDTO("test_email@email.com", "1234Qwerty!");

        // when: send a POST request to /login
        ResponseEntity<AuthTokenDTO> response =
                restTemplate.postForEntity(
                        getBaseUrl() + "/login", new HttpEntity<>(loginDTO), AuthTokenDTO.class);

        // then: expect a 200 OK status and a valid token in the response body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AuthTokenDTO authTokenDto = response.getBody();
        assertNotNull(authTokenDto, "The response body should not be null");
        assertNotNull(authTokenDto.getToken(), "The token should not be null");
    }

    @Test
    void shouldNotLoginBecauseOfWrongEmail() {
        // given: wrong email
        LoginDTO loginDTO = new LoginDTO("wrong_email@example.com", "1234Qwerty!");

        // when: send a POST request to /login
        ResponseEntity<ErrorDTO> response =
                restTemplate.postForEntity(
                        getBaseUrl() + "/login", new HttpEntity<>(loginDTO), ErrorDTO.class);

        // then: expect a 400 BAD_REQUEST status and Error response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDTO errorDto = response.getBody();
        assertEquals("Wrong credentials", errorDto.getMessage());
    }

    @Test
    void shouldNotLoginBecauseOfWrongPassword() {
        // given: wrong credentials
        LoginDTO loginDto = new LoginDTO("test_email@email.com", "IAmWrongPassword!");

        // when: send a POST request to /login
        ResponseEntity<ErrorDTO> response =
                restTemplate.postForEntity(
                        getBaseUrl() + "/login", new HttpEntity<>(loginDto), ErrorDTO.class);

        // then: expect a 400 BAD_REQUEST status and Error response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDTO errorDTO = response.getBody();
        assertEquals("Wrong credentials", errorDTO.getMessage());
    }

}
