package com.android.personal_financial.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.android.personal_financial.model.UserAccount;
import com.android.personal_financial.repository.UserAccountRepository;

@Service
public class UserAccountService {
    private final UserAccountRepository userRepository;
    private final PasswordService passwordService;

    public UserAccountService(UserAccountRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public void registerUser(UserAccount userAccount) {
        userRepository.save(userAccount);
    }

    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isValidUser(String username, String password) {
        UserAccount userAccount = userRepository.findByUsername(username);
        if (userAccount == null) {
            return false; // User does not exist
        }
    
        return passwordService.isPasswordValid(password, userAccount.getPassword());
    }

    public Integer getUserIdByUsername(String username) {
            UserAccount user = userRepository.findUserByUsername(username);
            Integer user_id = Integer.valueOf(user.getUserId());
        return user_id;
    }

    public UserAccount getUserByUsername(String username) {
        return null;
    }

    public String generateResetCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // Generate a random number between 100000 and 999999
        return String.valueOf(code);
    }

    public void updateUserAccount(UserAccount userAccount) {
        userRepository.save(userAccount);
    }

    public UserAccount getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
}

