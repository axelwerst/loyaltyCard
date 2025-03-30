package academy.codilas.loyaltycard.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.domain.repository.AdminRepository;
import academy.codilas.loyaltycard.mapper.AdminMapper;
import academy.codilas.loyaltycard.repository.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private final AdminMapper adminMapper;
    private final AdminJpaRepository adminJpaRepository;

    @Autowired
    public AdminRepositoryImpl(AdminMapper adminMapper, AdminJpaRepository adminJpaRepository) {
        this.adminMapper = adminMapper;
        this.adminJpaRepository = adminJpaRepository;
    }

    @Override
    public Admin save(Admin admin) {
        AdminEntity adminEntity = adminMapper.toEntity(admin);

        AdminEntity savedAdminEntity = adminJpaRepository.save(adminEntity);

        return adminMapper.toDomain(savedAdminEntity);
    }

    @Override
    public List<Admin> findAll() {
        return adminJpaRepository.findAll().stream().map(adminMapper::toDomain).toList();
    }

    @Override
    public Optional<Admin> findById(UUID adminId) {
        return adminJpaRepository.findById(adminId).map(adminMapper::toDomain);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminJpaRepository.findByEmail(email).map(adminMapper::toDomain);
    }
    @Override
    public Optional<Admin> findByEmailIgnoreCase(String email) {
        return adminJpaRepository.findByEmail(email.toLowerCase()).map(adminMapper::toDomain);
    }

    @Override
    public void delete(Admin admin) {
        adminJpaRepository.deleteById(admin.getId());
    }
}
