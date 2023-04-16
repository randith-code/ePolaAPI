package com.epola.ePolaAPI.services;

import com.epola.ePolaAPI.filter.JwtService;
import com.epola.ePolaAPI.model.Buyer;
import com.epola.ePolaAPI.model.Role;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.model.Transport;
import com.epola.ePolaAPI.repository.BuyerRepository;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.repository.TransportRepository;
import com.epola.ePolaAPI.resource.AuthenticationRequest;
import com.epola.ePolaAPI.resource.AuthenticationResponse;
import com.epola.ePolaAPI.resource.BuyerRequest;
import com.epola.ePolaAPI.resource.SellerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SellerRepository sellerRepository;
    private final TransportRepository transportRepository;
    private final BuyerRepository buyerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        Optional<Seller> seller = sellerRepository.findSellerByContact_Email(authenticationRequest.getEmail());
        Optional<Buyer> buyer = buyerRepository.findBuyerByEmail(authenticationRequest.getEmail());
        Optional<Transport> transport = transportRepository.findTransportByContact_Email(authenticationRequest.getEmail());


        if(seller.isPresent()){
          var user = seller.get();
          var jwtToken = jwtService.generateToken(user);
          return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else if (buyer.isPresent()) {
           var user = buyer.get();
           var jwtToken = jwtService.generateToken(user);
           return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else if(transport.isPresent()){
          var user = transport.get();
          var jwtToken = jwtService.generateToken(user);
          return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else{
           return null;
        }
//        var seller = sellerRepository.findSellerByContact_Email(authenticationRequest.getEmail())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(seller);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
    }

    public AuthenticationResponse registerSeller(SellerRequest sellerRequest){

        Seller seller = new Seller();
        seller.setUserName(sellerRequest.getUserName());
        seller.setPassword(passwordEncoder.encode(sellerRequest.getPassword()));
        seller.setContact(sellerRequest.getContact());
        seller.setLocation(sellerRequest.getLocation());
        seller.setRole(Role.SELLER);

        sellerRepository.save(seller);

        var jwtToken = jwtService.generateToken(seller);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerBuyer(BuyerRequest buyerRequest){
        Buyer buyer = new Buyer();
        buyer.setUserName(buyerRequest.getUsername());
        buyer.setPassword(passwordEncoder.encode(buyerRequest.getPassword()));
        buyer.setEmail(buyerRequest.getEmail());

        buyerRepository.save(buyer);

        var jwtToken = jwtService.generateToken(buyer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
