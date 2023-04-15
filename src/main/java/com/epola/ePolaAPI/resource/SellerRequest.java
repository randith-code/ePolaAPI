package com.epola.ePolaAPI.resource;

import com.epola.ePolaAPI.model.Contact;
import com.epola.ePolaAPI.model.Item;
import com.epola.ePolaAPI.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class SellerRequest {
    private String userName;
    private String password;
    private Contact contact;
    private Location location;
    private String role;
}
