package com.example.serviceFakeNews.service;

import com.example.serviceFakeNews.entity.UserEntity;
import com.example.serviceFakeNews.exception.ApplicationError;
import com.example.serviceFakeNews.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil tokenUtil;


    public ResponseEntity responseEntity(String name, String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "Not valid email or password."), HttpStatus.UNAUTHORIZED);
        }
        UserEntity userDetails = (UserEntity) userService.loadUserByUsername(name);
        String token = tokenUtil.token(userDetails);
        return ResponseEntity.ok(token);
    }

    public String getName() {
        return ((UserEntity) getAuth().getPrincipal()).getUsername();
    }


    Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isLogIn() {
        return getAuth().isAuthenticated();
    }
}
