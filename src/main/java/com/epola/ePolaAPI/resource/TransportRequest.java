package com.epola.ePolaAPI.resource;

import com.epola.ePolaAPI.model.Contact;
import com.epola.ePolaAPI.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportRequest {
    private String userName;
    private String password;
    private Contact contact;
    private Location location;
}
