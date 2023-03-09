package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.resource.AuthRequest;
import com.epola.ePolaAPI.resource.AuthenticationResponce;
import com.epola.ePolaAPI.service.AuthenticationService;
import com.epola.ePolaAPI.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ePolaAPI")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    private final AuthenticationService service;
    private final SellerRepository sellerRepository;

    @PostMapping("/auth/authenticateUser")
    public ResponseEntity<AuthenticationResponce> registerSeller(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(service.authenticateUser(authRequest, sellerRepository));
    }
}
