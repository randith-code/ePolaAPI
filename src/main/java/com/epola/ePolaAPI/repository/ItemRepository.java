package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
