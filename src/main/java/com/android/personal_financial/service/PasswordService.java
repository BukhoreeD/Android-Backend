package com.android.personal_financial.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private static final int STRENGTH = 10; // The higher the strength, the more secure but slower the hashing process

    public String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH);
        return passwordEncoder.encode(password);
    }

    public boolean isPasswordValid(String password, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH);
        return passwordEncoder.matches(password, hashedPassword);
    }
}
