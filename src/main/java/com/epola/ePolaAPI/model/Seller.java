package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collation = "sellers")
public class Seller implements UserDetails {

    @Id
    private String sid;
    private String userName;
    private String password;
    private Date signInDate = new Date(System.currentTimeMillis());
    private Contact contact;
    private Location location;
    private Role role;

    public Seller(String username, String password, Set<GrantedAuthority> grantedAuthorities) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //check if application doesn't work
       return List.of(new SimpleGrantedAuthority(role.name().toString()));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
