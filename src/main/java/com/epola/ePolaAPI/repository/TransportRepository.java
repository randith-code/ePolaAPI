package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Transport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TransportRepository extends MongoRepository<Transport, String> {
    Optional<Transport> findTransportByContact_Email(String email);
}
