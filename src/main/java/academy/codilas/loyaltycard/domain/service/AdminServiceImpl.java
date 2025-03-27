package academy.codilas.loyaltycard.domain.service;


import java.util.*;

import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.domain.repository.AdminRepository;
import academy.codilas.loyaltycard.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(UUID adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);

        return admin.orElseThrow(() -> new AdminNotFoundException("Admin not found!"));
    }

    @Override
    public Admin updateAdmin(UUID adminId, Admin adminToUpdateFrom) {
        Admin admin = getAdminById(adminId);

        admin.update(adminToUpdateFrom);

        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(UUID adminId) {
        Admin admin = getAdminById(adminId);

        adminRepository.delete(admin);
    }
}