package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
}
