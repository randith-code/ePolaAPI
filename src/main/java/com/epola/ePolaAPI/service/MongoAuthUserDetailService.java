package com.epola.ePolaAPI.service;

import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.repository.SellerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {

    private final SellerRepository sellerRepository;

    public MongoAuthUserDetailService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    // ...
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       Optional <Seller> seller = Optional.ofNullable(sellerRepository.findSellerByUserName(userName));

       return seller.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}