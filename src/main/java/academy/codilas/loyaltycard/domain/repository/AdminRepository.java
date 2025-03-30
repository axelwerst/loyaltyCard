package academy.codilas.loyaltycard.domain.repository;

import academy.codilas.loyaltycard.domain.model.Admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminRepository {
    Admin save(Admin admin);

    List<Admin> findAll();

    Optional<Admin> findById(UUID adminId);

    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByEmailIgnoreCase(String email);

    void delete(Admin admin);
}