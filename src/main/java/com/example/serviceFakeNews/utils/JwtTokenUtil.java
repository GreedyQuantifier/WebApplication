package com.example.serviceFakeNews.utils;

import com.example.serviceFakeNews.entity.RoleEntity;
import com.example.serviceFakeNews.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;


@Component
public class JwtTokenUtil {

    @Value("${jwt.token.secret}")
    String secret;

    @Value("${jwt.token.lifetime}")
    Duration duration;

    public String token(UserEntity entity) {
        Date now = new Date();
        Date endLifeTime = new Date(now.getTime() + duration.toMillis());

        Map<String, Object> map = new HashMap<>();

        map.put("roles", List.of(entity.getRole()));

        return Jwts.builder()
                .setClaims(map)
                .setSubject(entity.getUsername())
                .setIssuedAt(now)
                .setExpiration(endLifeTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public String getUsername(String token) {
        return claims(token).getSubject();
    }

    public GrantedAuthority getRole(String token) {
        return () -> (String) claims(token).get("roles",List.class).get(0);
    }

    Claims claims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
