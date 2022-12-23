package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.model.Buyer;
import com.epola.ePolaAPI.repository.BuyerRepository;
import com.epola.ePolaAPI.resource.BuyerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BuyerController {

    private final BuyerRepository buyerrepository;

    public BuyerController(BuyerRepository buyerrepository) {
        this.buyerrepository = buyerrepository;
    }

    @GetMapping("/buyer")
    public ResponseEntity<List<Buyer>> getAllBuyers(){
        return ResponseEntity.ok(this.buyerrepository.findAll());
    }

    @PostMapping("/buyer")
    public ResponseEntity<Buyer> addBuyer(@RequestBody BuyerRequest buyerrequest){

        Buyer buyer = new Buyer();
        buyer.setUserName(buyerrequest.getUsername());
        buyer.setPassword(buyerrequest.getPassword());
        buyer.setEmail(buyerrequest.getEmail());

        return ResponseEntity.status(201).body(this.buyerrepository.save(buyer));
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
