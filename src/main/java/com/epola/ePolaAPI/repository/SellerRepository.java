package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String> {
     <Optinal>Seller findSellerByUserName(String userName);
}
