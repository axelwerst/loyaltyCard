package academy.codilas.loyaltycard.repository;

import java.util.Optional;
import java.util.UUID;

import academy.codilas.loyaltycard.repository.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminJpaRepository extends JpaRepository<AdminEntity, UUID> {

    Optional<AdminEntity> findByEmail(String email);
}
