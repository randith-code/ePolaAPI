package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.exceptions.ResourceNotFoundException;
import com.epola.ePolaAPI.filter.JwtService;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.resource.AuthenticationResponse;
import com.epola.ePolaAPI.resource.SellerRequest;
import com.epola.ePolaAPI.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")
@RequiredArgsConstructor
public class SellerController {

    private final SellerRepository sellerrepository;
    private final AuthenticationService service;


    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllSellers(){
        return ResponseEntity.ok(this.sellerrepository.findAll());
    }

    @PostMapping("auth/seller/register")
    public ResponseEntity<AuthenticationResponse> addSeller(@RequestBody SellerRequest sellerRequest){
        return  ResponseEntity.ok(service.registerSeller(sellerRequest));
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity getSellerById(@PathVariable String id){
        Optional<Seller> seller = this.sellerrepository.findById(id);

        if (seller.isPresent()){
            return ResponseEntity.ok(seller.get());
        }
        else{
            return ResponseEntity.ok("Seller with id " + id + " is not found");
        }
    }

    @PatchMapping("/seller/{id}")
    public ResponseEntity updateSellerInfo(@PathVariable String id, @RequestBody SellerRequest sellerRequest){
        Seller seller = this.sellerrepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Seller not exist with id: " + id));

        if(sellerRequest.getUserName() != null){
            seller.setUserName(sellerRequest.getUserName());
        }
        if (sellerRequest.getPassword() != null){
            seller.setPassword(sellerRequest.getPassword());

        }
        if (sellerRequest.getContact() != null){
            seller.setContact(sellerRequest.getContact());
        }
        if (sellerRequest.getLocation() != null){
            seller.setLocation(sellerRequest.getLocation());
        }

        return ResponseEntity.status(201).body(this.sellerrepository.save(seller));
    }

    @DeleteMapping("/seller/{id}")
    public ResponseEntity deleteSellerById(@PathVariable String id){
        Optional<Seller> seller = this.sellerrepository.findById(id);

        if (seller.isPresent()){
            this.sellerrepository.deleteById(id);
            return ResponseEntity.ok("Success");
        }
        else{
            return ResponseEntity.ok("Seller with id " + id + " is not found");
        }
    }

    @DeleteMapping("/seller")
    public ResponseEntity deleteAllSellers(){
        this.sellerrepository.deleteAll();
        return ResponseEntity.ok("All sellers are removed");
    }
}
