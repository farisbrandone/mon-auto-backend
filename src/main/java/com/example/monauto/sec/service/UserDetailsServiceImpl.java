package com.example.monauto.sec.service;

import com.example.monauto.entity.Seller;
import com.example.monauto.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SellerService sellerService;

    public UserDetailsServiceImpl(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Seller seller=sellerService.loadSellerByEmail(email);
        if(!seller.isActiveState()){
            throw new RuntimeException("Vous n'avez pas confirmez votre inscription par email");
        }
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        seller.getRoleSeller().forEach(roleSeller->authorities.add(new SimpleGrantedAuthority(roleSeller.getRoleName().getRole())));
        return new User(seller.getEmail(), seller.getPassword(), authorities);
    }
}
