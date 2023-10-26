package com.example.serviceFakeNews.service;

import com.example.serviceFakeNews.entity.UserEntity;
import com.example.serviceFakeNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(username);
        if (user != null) return user;

        throw new UsernameNotFoundException("User by email %s not found!".formatted(username));
    }
}

