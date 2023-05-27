package com.android.personal_financial.service;

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
    
}

