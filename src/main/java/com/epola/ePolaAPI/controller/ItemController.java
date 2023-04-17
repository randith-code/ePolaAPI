package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.model.Item;
import com.epola.ePolaAPI.repository.ItemRepository;
import com.epola.ePolaAPI.resource.ItemRequest;
import com.epola.ePolaAPI.resource.ReviewRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")
@CrossOrigin("http://localhost:3000")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.ok(this.itemRepository.findAll());
    }

    @PostMapping("/item")
    public ResponseEntity<Item> addItem(@RequestBody ItemRequest itemRequest){
        Item item = new Item();

        item.setIname(itemRequest.getIname());
        item.setOwnerID(itemRequest.getOwnerID());
        item.setPriceRate(itemRequest.getPriceRate());
        item.setDescription(itemRequest.getDescription());

        return ResponseEntity.status(201).body(this.itemRepository.save(item));
    }

    @GetMapping("/item/{iid}")
    public ResponseEntity<Item> getItem(@PathVariable String iid){
        Optional<Item> item = itemRepository.findById(iid);

        if (item.isPresent()){
            return ResponseEntity.ok(item.get());
        }
        else{
            return null;
        }
    }

    @DeleteMapping("/item/{iid}")
    public ResponseEntity deleteItem(@PathVariable String iid){
        Optional<Item> item = itemRepository.findById(iid);

        if (item.isPresent()){
            this.itemRepository.deleteById(iid);
            return ResponseEntity.ok("Success");
        }
        else {
            return ResponseEntity.ok("Item with id " + iid + " is not found");
        }
    }
}
