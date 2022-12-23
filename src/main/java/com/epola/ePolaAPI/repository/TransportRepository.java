package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Transport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportRepository extends MongoRepository<Transport, String> {
}
