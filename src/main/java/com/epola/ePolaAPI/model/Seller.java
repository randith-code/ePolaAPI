package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document("sellers")
public class Seller {

    @Id
    private String sid;
    private String userName;
    private String password;
    private Date signInDate = new Date(System.currentTimeMillis());
    private Contact contact;
    private Location location;
}
