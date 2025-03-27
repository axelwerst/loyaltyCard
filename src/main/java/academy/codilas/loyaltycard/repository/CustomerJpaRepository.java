package academy.codilas.loyaltycard.repository;

import java.util.Optional;
import java.util.UUID;

import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByEmail(String email);
}
