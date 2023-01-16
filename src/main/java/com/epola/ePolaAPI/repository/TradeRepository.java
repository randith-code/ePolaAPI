package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TradeRepository extends MongoRepository<Trade, String> {

    List<Trade> findTradesByBid(String bid);
    List<Trade> findByOwnerId(String ownerId);
}
