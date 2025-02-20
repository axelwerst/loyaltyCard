package academy.codilas.loyaltycard;

import academy.codilas.loyaltycard.service.AdminRepository;
import academy.codilas.loyaltycard.service.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void shouldReturnTrueWhenAdminLogsInWithCorrectPassword() {
        // Arrange
        String username = "admin";
        String rawPassword = "securePass";
        String encodedPassword = "$2a$10$somethinghashed"; // Захешированный пароль в БД
        PulsarProperties.Admin admin = new PulsarProperties.Admin();

        when(adminRepository.findByUsername(username)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        // Act
        boolean result = loginService.loginAdmin(username, rawPassword);

        // Assert
        assertTrue(result);
        verify(adminRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
    }

    @Test
    void shouldReturnFalseWhenAdminLogsInWithWrongPassword() {
        // Arrange
        String username = "admin";
        String rawPassword = "wrongPass";
        String encodedPassword = "$2a$10$somethinghashed";
        PulsarProperties.Admin admin = new PulsarProperties.Admin();

        when(adminRepository.findByUsername(username)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // Act
        boolean result = loginService.loginAdmin(username, rawPassword);

        // Assert
        assertFalse(result);
        verify(adminRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
    }

    @Test
    void shouldReturnFalseWhenAdminNotFound() {
        // Arrange
        String username = "unknownAdmin";
        String rawPassword = "anyPassword";

        when(adminRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        boolean result = loginService.loginAdmin(username, rawPassword);

        // Assert
        assertFalse(result);
        verify(adminRepository, times(1)).findByUsername(username);
        verifyNoInteractions(passwordEncoder);
    }
}