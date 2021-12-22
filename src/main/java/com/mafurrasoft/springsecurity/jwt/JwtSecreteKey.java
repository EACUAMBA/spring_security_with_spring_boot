package com.mafurrasoft.springsecurity.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecreteKey {
    private final JwtConfig jwtConfig;

    @Autowired
    public JwtSecreteKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecretKey getSecurityKeyForSigin() {
        return Keys.hmacShaKeyFor(this.jwtConfig.getSecretKey().getBytes());
    }
}
