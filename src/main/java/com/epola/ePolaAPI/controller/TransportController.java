package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.exceptions.ResourceNotFoundException;
import com.epola.ePolaAPI.model.Transport;
import com.epola.ePolaAPI.repository.TransportRepository;
import com.epola.ePolaAPI.resource.TransportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")
@CrossOrigin("http://localhost:3000")
public class TransportController {
    private final TransportRepository transportRepository;

    public TransportController(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }


    @GetMapping("/transport")
    public ResponseEntity<List<Transport>> getAllTransports(){
        return ResponseEntity.ok(this.transportRepository.findAll());
    }

    @PostMapping("/transport")
    public ResponseEntity<Transport> addTransport(@RequestBody TransportRequest transportRequest){
        Transport transport = new Transport();

        transport.setUserName(transportRequest.getUserName());
        transport.setPassword(transportRequest.getPassword());
        transport.setContact(transportRequest.getContact());
        transport.setLocation(transportRequest.getLocation());
        transport.setRole(transportRequest.getRole());


        return ResponseEntity.status(201).body(this.transportRepository.save(transport));
    }

    @GetMapping("/transport/{tid}")
    public ResponseEntity getTransportById(@PathVariable String tid){
        Optional<Transport> transport = this.transportRepository.findById(tid);

        if (transport.isPresent()){
            return ResponseEntity.ok(transport.get());
        }
        else{
            return ResponseEntity.ok("Transport Service with id " + tid + " is not found");
        }
    }

    @PatchMapping("/transport/{tid}")
    public ResponseEntity updateTransportInfo(@PathVariable String tid, @RequestBody TransportRequest transportRequest ){
        Transport transport = this.transportRepository.findById(tid).
                orElseThrow(() -> new ResourceNotFoundException("Transport Service not exist with id: " + tid));
        if(transportRequest.getUserName() != null){
            transport.setUserName(transportRequest.getUserName());
        }
        if (transportRequest.getPassword() != null){
            transport.setPassword(transportRequest.getPassword());
        }
        if (transportRequest.getContact() != null){
            transport.setContact(transportRequest.getContact());
        }
        if (transportRequest.getLocation() != null){
            transport.setLocation(transportRequest.getLocation());
        }

        return ResponseEntity.status(201).body(this.transportRepository.save(transport));
    }

    @DeleteMapping("/transport/{tid}")
    public ResponseEntity deleteTransportById(@PathVariable String tid){
        Optional<Transport> transport = this.transportRepository.findById(tid);

        if (transport.isPresent()){
            this.transportRepository.deleteById(tid);
            return ResponseEntity.ok("Success");
        }
        else{
            return ResponseEntity.ok("Transport Service with id " + tid + " is not found");
        }
    }

    @DeleteMapping("/transport")
    public ResponseEntity deleteAllTransport(){
        this.transportRepository.deleteAll();
        return ResponseEntity.ok("All transport Services are removed");
    }
}