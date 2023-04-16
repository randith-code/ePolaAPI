package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.model.Buyer;
import com.epola.ePolaAPI.repository.BuyerRepository;
import com.epola.ePolaAPI.resource.AuthenticationResponse;
import com.epola.ePolaAPI.resource.BuyerRequest;
import com.epola.ePolaAPI.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")

public class BuyerController {

    private final BuyerRepository buyerrepository;
    private final AuthenticationService service;

    public BuyerController(BuyerRepository buyerrepository, AuthenticationService service) {
        this.buyerrepository = buyerrepository;
        this.service = service;
    }

    @GetMapping("/buyer")
    public ResponseEntity<List<Buyer>> getAllBuyers(){
        return ResponseEntity.ok(this.buyerrepository.findAll());
    }

    @PostMapping("/auth/buyer/register")
    public ResponseEntity<AuthenticationResponse> addBuyer(@RequestBody BuyerRequest buyerrequest){

        return ResponseEntity.ok(service.registerBuyer(buyerrequest));
    }

    @GetMapping("/buyer/{id}")
    public ResponseEntity getBuyerById(@PathVariable String id){

        Optional<Buyer> buyer = this.buyerrepository.findById(id);

        if (buyer.isPresent()){
            return ResponseEntity.ok(buyer.get());
        }
        else{
            return ResponseEntity.ok("Buyer with id " + id + " is not found");
        }
    }

    @DeleteMapping("/buyer/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        Optional<Buyer> buyer = this.buyerrepository.findById(id);

        if (buyer.isPresent()){
            this.buyerrepository.deleteById(id);
            return ResponseEntity.ok("Success");
        }
        else {
            return ResponseEntity.ok("Buyer with id " + id + " is not found");
        }

    }
}
