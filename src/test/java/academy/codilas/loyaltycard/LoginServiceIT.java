package academy.codilas.loyaltycard;

import academy.codilas.loyaltycard.service.AdminRepository;
import academy.codilas.loyaltycard.service.LoginServiceImpl;
import academy.codilas.loyaltycard.service.domain.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class LoginServiceIT {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Admin testAdmin;

    @BeforeEach
    void setUp() {
        // Создаём тестового администратора
        testAdmin = new Admin(UUID.randomUUID(), "admin", passwordEncoder.encode("password123"));
        adminRepository.save(testAdmin);
    }

    @Test
    void shouldLoginSuccessfullyWithCorrectCredentials() {
        // Act
        boolean result = loginService.loginAdmin("admin", "password123");

        // Assert
        assertTrue(result, "Логин с правильным паролем должен быть успешным");
    }

    @Test
    void shouldFailLoginWithIncorrectPassword() {
        // Act
        boolean result = loginService.loginAdmin("admin", "wrongPassword");

        // Assert
        assertFalse(result, "Логин с неверным паролем должен быть отклонён");
    }

    @Test
    void shouldFailLoginWhenUserDoesNotExist() {
        // Act
        boolean result = loginService.loginAdmin("unknownAdmin", "password123");

        // Assert
        assertFalse(result, "Логин несуществующего администратора должен быть отклонён");
    }
}