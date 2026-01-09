package com.project.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.model.Investor;
import com.project.repository.InvestorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private InvestorRepository investorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Investor investor = investorRepository.findByEmail(email);
        if (investor == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new User(investor.getEmail(), investor.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(investor.getRole())));
    }
}
