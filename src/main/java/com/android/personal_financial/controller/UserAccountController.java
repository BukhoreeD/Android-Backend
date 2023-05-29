package com.android.personal_financial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.personal_financial.dto.UserAccountDTO;
import com.android.personal_financial.dto.UserLoginDTO;
import com.android.personal_financial.model.UserAccount;
import com.android.personal_financial.service.PasswordService;
import com.android.personal_financial.service.TokenService;
import com.android.personal_financial.service.UserAccountService;

@RestController
@RequestMapping("/auth")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final TokenService tokenService;
    private final PasswordService passwordService;

    public UserAccountController(UserAccountService userAccountService, TokenService tokenService, PasswordService passwordService) {
        this.userAccountService = userAccountService;
        this.tokenService = tokenService;
        this.passwordService = passwordService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAccountDTO userAccountDTO) {
        if (userAccountService.isUserExists(userAccountDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        String hashedPassword = passwordService.hashPassword(userAccountDTO.getPassword());

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccountDTO.getUsername());
        userAccount.setPassword(hashedPassword);
        userAccount.setEmail(userAccountDTO.getEmail());

        userAccountService.registerUser(userAccount);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        if (!userAccountService.isValidUser(userLoginDTO.getUsername(), userLoginDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Generate a token
        String token = tokenService.generateToken(userLoginDTO.getUsername());

        // Return the token in the response body
        return ResponseEntity.ok(token);
    }
}
