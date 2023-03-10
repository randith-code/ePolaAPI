package com.epola.ePolaAPI.service;

import com.epola.ePolaAPI.model.Role;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.resource.AuthRequest;
import com.epola.ePolaAPI.resource.AuthenticationResponce;
import com.epola.ePolaAPI.resource.SellerRequest;
import com.epola.ePolaAPI.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponce registerSeller(SellerRequest sellerRequest, SellerRepository repository) {
        Seller seller = new Seller();
        seller.setUserName(sellerRequest.getUserName());
        seller.setPassword(passwordEncoder.encode(sellerRequest.getPassword()));
        seller.setContact(sellerRequest.getContact());
        seller.setLocation(sellerRequest.getLocation());
        seller.setRole(Role.SELLER);

        repository.save(seller);

        var jwtToken = jwtUtil.generateToken(seller);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponce authenticateUser(AuthRequest authRequest, SellerRepository repository){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
        );

        var user = repository.findSellerByUserName(authRequest.getUserName())
                .orElseThrow();
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }
}
