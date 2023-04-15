package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document("buyers")
public class Buyer {

    @Id
    private String bid;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Date SignInDate = new Date(System.currentTimeMillis());
    private String role;
}
