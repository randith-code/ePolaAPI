package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document("authorities")
public class Authority implements GrantedAuthority {
    @Id
    private String authId;
    private String authority;
    private Seller seller;
    private Buyer buyer;
    private Transport transport;

    public Authority(String auth){
        this.authority = auth;
    }
}
