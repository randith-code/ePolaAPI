package com.epola.ePolaAPI.services;

import com.epola.ePolaAPI.filter.JwtService;
import com.epola.ePolaAPI.model.Role;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.resource.AuthenticationRequest;
import com.epola.ePolaAPI.resource.AuthenticationResponse;
import com.epola.ePolaAPI.resource.SellerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SellerRepository sellerRepository;
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

        var seller = sellerRepository.findSellerByContact_Email(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(seller);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(SellerRequest sellerRequest){

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
}
