package academy.codilas.loyaltycard.repository;

import academy.codilas.loyaltycard.repository.entity.CustomerEmtity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEmtity, UUID> {

    Optional<CustomerEmtity> findById(UUID uuid);

}
