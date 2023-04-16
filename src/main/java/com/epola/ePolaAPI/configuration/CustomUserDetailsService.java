package com.epola.ePolaAPI.configuration;


import com.epola.ePolaAPI.model.Buyer;
import com.epola.ePolaAPI.model.Seller;
import com.epola.ePolaAPI.model.Transport;
import com.epola.ePolaAPI.repository.BuyerRepository;
import com.epola.ePolaAPI.repository.SellerRepository;
import com.epola.ePolaAPI.repository.TransportRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final TransportRepository transportRepository;

    public CustomUserDetailsService(SellerRepository sellerRepository, BuyerRepository buyerRepository, TransportRepository transportRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.transportRepository = transportRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Seller> seller = sellerRepository.findSellerByContact_Email(username);
        Optional<Buyer> buyer = buyerRepository.findBuyerByEmail(username);
        Optional<Transport> trade =transportRepository.findTransportByContact_Email(username);

        if (seller.isPresent()) {
            Seller user = seller.get();
            return User.builder()
                    .username(user.getContact().getEmail())
                    .password(user.getPassword())
                    .roles("SELLER")
                    .build();
        } else if (buyer.isPresent()) {
            Buyer user = buyer.get();
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles("BUYER")
                    .build();
        } else if (trade.isPresent()) {
            Transport user = trade.get();
            return User.builder()
                    .username(user.getContact().getEmail())
                    .password(user.getPassword())
                    .roles("TRANSPORT")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
