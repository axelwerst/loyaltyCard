package academy.codilas.loyaltycard.service;


import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginServiceImpl {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean loginAdmin(String username, String rawPassword) {
        Optional<PulsarProperties.Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt.isPresent() && passwordEncoder.matches(rawPassword, String.valueOf(adminOpt.get().getClass()));
    }
}