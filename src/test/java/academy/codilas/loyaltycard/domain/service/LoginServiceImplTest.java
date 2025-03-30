package academy.codilas.loyaltycard.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import java.util.Optional;

import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.domain.repository.AdminRepository;
import academy.codilas.loyaltycard.exception.AdminNotFoundException;
import academy.codilas.loyaltycard.exception.PasswordIncorrectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private LoginServiceImpl loginService;

//  @BeforeEach
//  public void setUp() {
//    loginService = new LoginServiceImpl(passwordEncoder, adminepository, tokenService);
//  }

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        // given
        String email = "test@example.com";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(encodedPassword);

        when(adminRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(tokenService.createToken(admin)).thenReturn("token123");

        // when
        String token = loginService.login(email, rawPassword);

        // then
        assertEquals("token123", token);

        verify(adminRepository).findByEmailIgnoreCase(email);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(tokenService).createToken(admin);
    }

    @Test
    void shouldThrowAdminNotFoundExceptionWhenAdminDoesNotExist() {
        // given
        String email = "nonexistent@example.com";
        String rawPassword = "password";

        when(adminRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        // when & then
        assertThrows(AdminNotFoundException.class, () -> loginService.login(email, rawPassword));

        verify(adminRepository).findByEmailIgnoreCase(email);
        verifyNoMoreInteractions(passwordEncoder, tokenService);
    }

    @Test
    void shouldThrowPasswordIncorrectExceptionWhenPasswordDoesNotMatch() {
        // given
        String email = "test@example.com";
        String rawPassword = "wrongPassword";
        String encodedPassword = "encodedPassword";
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(encodedPassword);

        when(adminRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // when & then
        assertThrows(PasswordIncorrectException.class, () -> loginService.login(email, rawPassword));

        verify(adminRepository).findByEmailIgnoreCase(email);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verifyNoInteractions(tokenService);
    }
}
