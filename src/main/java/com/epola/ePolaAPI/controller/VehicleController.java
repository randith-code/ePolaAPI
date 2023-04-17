package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.model.Vehicle;
import com.epola.ePolaAPI.repository.VehicleRepository;
import com.epola.ePolaAPI.resource.VehicleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")
@CrossOrigin("http://localhost:3000")
public class VehicleController {

    private final VehicleRepository vehicleRepository;


    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/vehicle")
    public ResponseEntity<List<Vehicle>> getAllvehicles(){
        return ResponseEntity.ok(this.vehicleRepository.findAll());
    }

    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> addvehicle(@RequestBody VehicleRequest vehicleRequest){
        Vehicle vehicle = new Vehicle();

        vehicle.setOwnerId(vehicleRequest.getOwnerId());
        vehicle.setVehicleModel(vehicleRequest.getVehicleModel());
        vehicle.setRegNo(vehicleRequest.getRegNo());
        vehicle.setCapacity(vehicleRequest.getCapacity());
        vehicle.setPriceRate(vehicleRequest.getPriceRate());

        return ResponseEntity.status(201).body(this.vehicleRepository.save(vehicle));
    }

    @GetMapping("/vehicle/{vid}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String vid){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vid);

        if (vehicle.isPresent()){
            return ResponseEntity.ok(vehicle.get());
        }
        else{
            return null;
        }
    }

    @DeleteMapping("/vehicle/{vid}")
    public ResponseEntity deleteVehicle(@PathVariable String vid){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vid);

        if (vehicle.isPresent()){
            this.vehicleRepository.deleteById(vid);
            return ResponseEntity.ok("Success");
        }
        else {
            return ResponseEntity.ok("Vehicle with id " + vid + " is not found");
        }
    }
}
