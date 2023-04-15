package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
    Optional<Buyer> findBuyerByEmail(String email);
}
