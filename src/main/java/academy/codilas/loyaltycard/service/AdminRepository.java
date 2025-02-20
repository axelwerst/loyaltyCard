package academy.codilas.loyaltycard.service;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<KafkaProperties.Admin, UUID> {
    Optional<PulsarProperties.Admin> findByUsername(String username);
}