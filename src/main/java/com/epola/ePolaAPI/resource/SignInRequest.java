package com.epola.ePolaAPI.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
    private String role;
}
