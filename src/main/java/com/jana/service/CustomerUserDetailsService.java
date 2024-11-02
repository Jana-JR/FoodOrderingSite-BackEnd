package com.jana.service;

import com.jana.model.USER_ROLE;
import com.jana.model.User;
import com.jana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService { // This will prevent auto generating password for users every time

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user!= null) {
            throw new UsernameNotFoundException("User not found with this email" +username);

        }

        USER_ROLE role= user.getRole();

        if(role==null) role = USER_ROLE.ROLE_CUSTOMER;
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities );


    }
}
