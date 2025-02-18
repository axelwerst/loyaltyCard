package academy.codilas.loyaltycard.repository;


import academy.codilas.loyaltycard.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Event.ID> {

    Optional<CustomerEntity> findById(UUID uuid);

}
