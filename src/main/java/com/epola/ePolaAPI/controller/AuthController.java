package com.epola.ePolaAPI.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.resource.AuthRequest;
import com.epola.ePolaAPI.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ePolaAPI")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest){
        try{
            Authentication authentication =authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authRequest.getUserName(), authRequest.getPassword()
                            )
                    );
            Seller seller = (Seller) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(seller)
                    )
                    .body(seller); // change this because it's expose the password
        }
        catch(BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
