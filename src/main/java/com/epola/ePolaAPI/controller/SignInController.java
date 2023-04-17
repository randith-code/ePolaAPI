package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.model.Buyer;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.model.Transport;
import com.epola.ePolaAPI.repository.BuyerRepository;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.repository.TransportRepository;
import com.epola.ePolaAPI.resource.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ePolaAPI")
@CrossOrigin("http://localhost:3000")
public class SignInController {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final TransportRepository transportRepository;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){

        if(Objects.equals(signInRequest.getRole(), "SELLER")){
            Optional<Seller> sellerInfo = sellerRepository.findSellerByContact_Email(signInRequest.getEmail());
             if( sellerInfo.isPresent()){
                 if(sellerInfo.get().getPassword().equals(signInRequest.getPassword())){
                     return ResponseEntity.ok("authenticated");
                 }
                else{
                    return ResponseEntity.ok("Wrong password");
                 }
             }
             else{
                 return ResponseEntity.ok("User not found");
             }
        }
        else if (Objects.equals(signInRequest.getRole(), "TRANSPORT")) {
            Optional<Transport> transportInfo = transportRepository.findByContact_Email(signInRequest.getEmail());
            if (transportInfo.isPresent()){
                if(transportInfo.get().getPassword().equals(signInRequest.getPassword())){
                    return ResponseEntity.ok("authenticated");
                }
                else{
                    return ResponseEntity.ok("Wrong password");
                }
            }
            else {
                return ResponseEntity.ok("User not found");
            }
        }
        else{
            Optional<Buyer> buyerInfo = buyerRepository.findBuyerByEmail(signInRequest.getEmail());
            if(buyerInfo.isPresent()){
                if(buyerInfo.get().getPassword().equals(signInRequest.getPassword())){
                    return ResponseEntity.ok("authenticated");
                }
                else{
                    return ResponseEntity.ok("Wrong password");
                }
            }
            else{
                return ResponseEntity.ok("User not found");
            }
        }
    }
}
