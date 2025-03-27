package academy.codilas.loyaltycard.domain.service;

import academy.codilas.loyaltycard.domain.repository.AdminRepository;
import academy.codilas.loyaltycard.exception.AdminNotFoundException;
import academy.codilas.loyaltycard.exception.PasswordIncorrectException;
import academy.codilas.loyaltycard.repository.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public LoginServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public boolean loginAdmin(String email, String rawPassword) {
        Optional<AdminEntity> adminOpt = adminRepository.findByEmail(email);

        if (adminOpt.isPresent()) {
            AdminEntity admin = adminOpt.get();
            return passwordEncoder.matches(rawPassword, admin.getPassword());
        }

        return false;
    }

    @Override
    public String login(String email, String password) {
        Optional<AdminEntity> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isEmpty()) {
            throw new AdminNotFoundException("Admin not found");
        }
        if (!passwordEncoder.matches(password, adminOpt.get().getPassword())) {
            throw new PasswordIncorrectException(email);
        }

        return tokenService.createToken(adminOpt.get());
    }
}