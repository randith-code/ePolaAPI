package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.exceptions.ResourceNotFoundException;
import com.epola.ePolaAPI.model.Item;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.resource.ItemRequest;
import com.epola.ePolaAPI.resource.ReviewRequest;
import com.epola.ePolaAPI.resource.SellerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class SellerController {

    private final SellerRepository sellerrepository;

    public SellerController(SellerRepository sellerrepository) {
        this.sellerrepository = sellerrepository;
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllSellers(){
        return ResponseEntity.ok(this.sellerrepository.findAll());
    }

    @PostMapping("/seller")
    public ResponseEntity<Seller> addSeller(@RequestBody SellerRequest sellerRequest){
        Seller seller = new Seller();
        seller.setUserName(sellerRequest.getUserName());
        seller.setPassword(sellerRequest.getPassword());
        seller.setContact(sellerRequest.getContact());
        seller.setLocation(sellerRequest.getLocation());
        seller.setItemList(sellerRequest.getItems());

        return ResponseEntity.status(201).body(this.sellerrepository.save(seller));
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity getSellerById(@PathVariable String id){
        Optional<Seller> seller = this.sellerrepository.findById(id);

        if (seller.isPresent()){
            return ResponseEntity.ok(seller.get());
        }
        else{
            return ResponseEntity.ok("Buyer with id " + id + " is not found");
        }
    }

    @DeleteMapping("/seller/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        Optional<Seller> seller = this.sellerrepository.findById(id);

        if (seller.isPresent()){
            this.sellerrepository.deleteById(id);
            return ResponseEntity.ok("Success");
        }
        else{
            return ResponseEntity.ok("Buyer with id " + id + " is not found");
        }
    }

    @PostMapping("/seller/{id}")
    public ResponseEntity<Seller> addItem(@PathVariable String id, @RequestBody ItemRequest itemRequest) throws ResourceNotFoundException {
        Seller seller = this.sellerrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ id));
        Item itm = new Item();
        //seller.getItems().get(0);
        itm.setPostedDate(new Date());
        itm.setIname(itemRequest.getIname());
        itm.setPriceRate(itemRequest.getPriceRate());
        itm.setDescription(itemRequest.getDescription());
        seller.setItems(itm);
        final Seller updatedSeller = sellerrepository.save(seller);
        return ResponseEntity.ok(updatedSeller);
    }

    @PostMapping("/seller/{id}?{item}")
    public ResponseEntity<Seller> addReview(@PathVariable String id,@PathVariable String item, @RequestBody ReviewRequest reviewRequest) throws ResourceNotFoundException {
        Seller seller = this.sellerrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ id));
        ArrayList<Item> itm = seller.getItems();

        Item it = new Item();

        for(Item i : itm){
            if(i.getIname().equals(item)){
                it = i;
            }
        }

//        seller.setItems(itm);
        final Seller updatedSeller = sellerrepository.save(seller);
        return ResponseEntity.ok(updatedSeller);
    }
}
