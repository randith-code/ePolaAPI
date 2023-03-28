package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, String> {
    Optional<Seller> findSellerByContact_Email(String email);
}
