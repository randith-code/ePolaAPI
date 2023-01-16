package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
