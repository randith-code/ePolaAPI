package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.resource.AuthenticationRequest;
import com.epola.ePolaAPI.resource.AuthenticationResponse;
import com.epola.ePolaAPI.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ePolaAPI")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/auth/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
    return  ResponseEntity.ok(service.authenticate(authenticationRequest));
  }

}
