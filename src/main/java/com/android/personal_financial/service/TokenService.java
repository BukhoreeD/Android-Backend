package com.android.personal_financial.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {
    private static final String SIGNING_KEY = "Aa001Bb3378";

    public String generateToken(String username) {
        // Set the token expiration time (e.g., 1 hour from now)
        // long expirationTimeMillis = System.currentTimeMillis() + 3600000;

        // Set the token expiration time to a distant future date
        // long expirationTimeMillis = System.currentTimeMillis() + Long.MAX_VALUE;

        // Build the JWT token using Auth0's approach
        String token = JWT.create()
                .withClaim("principal", username)
                .withClaim("role", "USER")
                // .withExpiresAt(new Date(expirationTimeMillis))
                .sign(Algorithm.HMAC256(SIGNING_KEY));
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        try {
            // Create a JWT verifier with Auth0's signing key
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SIGNING_KEY)).build();

            // Verify and decode the token
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException e) {
            // Token verification failed
            return null;
        }
    }
}
