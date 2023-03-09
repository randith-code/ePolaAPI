package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document("sellers")
public class Seller implements UserDetails {

    @Id
    private String sid;
    private String userName;
    private String password;
    private Date signInDate = new Date(System.currentTimeMillis());
    private Contact contact;
    private Location location;
    private List<Authority> authorities = new ArrayList<>();

    public Seller(String username, String password, Set<GrantedAuthority> grantedAuthorities) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //check if application doesn't work
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new Authority("ROLE_SELLER"));
        return roles;
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
