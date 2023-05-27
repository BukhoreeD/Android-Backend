package com.android.personal_financial.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a new secret key

    public String generateToken(String username) {
        // // Set the token expiration time (e.g., 1 hour from now)
        // long expirationTimeMillis = System.currentTimeMillis() + 3600000;

        // Set the token expiration time to a distant future date
        long expirationTimeMillis = System.currentTimeMillis() + Long.MAX_VALUE;

        // Build the JWT token
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SECRET_KEY)
                .compact();

        return token;
    }

    // Add additional methods for token validation, parsing, etc.
}

