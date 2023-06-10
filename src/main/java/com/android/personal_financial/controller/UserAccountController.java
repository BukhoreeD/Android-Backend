package com.android.personal_financial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.personal_financial.dto.ForgotPasswordDTO;
import com.android.personal_financial.dto.ResetPasswordDTO;
import com.android.personal_financial.dto.UserAccountDTO;
import com.android.personal_financial.dto.UserLoginDTO;
import com.android.personal_financial.model.UserAccount;
import com.android.personal_financial.service.EmailService;
import com.android.personal_financial.service.PasswordService;
import com.android.personal_financial.service.TokenService;
import com.android.personal_financial.service.UserAccountService;

@RestController
@RequestMapping("/auth")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final TokenService tokenService;
    private final PasswordService passwordService;
    private final EmailService emailService;

    public UserAccountController(UserAccountService userAccountService, TokenService tokenService,
            PasswordService passwordService, EmailService emailService) {
        this.userAccountService = userAccountService;
        this.tokenService = tokenService;
        this.passwordService = passwordService;
        this.emailService = emailService;
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
        if (!userAccountService.isValidUser(userLoginDTO.getUsername(),
                userLoginDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Generate a token
        String token = tokenService.generateToken(userLoginDTO.getUsername());

        // Return the token in the response body
        return ResponseEntity.ok(token);
    }

    @GetMapping("/users/{username}/id")
    public ResponseEntity<Integer> getUserIdByUsername(@PathVariable("username") String username) {
        Integer userId = userAccountService.getUserIdByUsername(username);

        if (userId == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userId);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        UserAccount userAccount = userAccountService.getUserByEmail(forgotPasswordDTO.getEmail());

        if (userAccount == null) {
            return ResponseEntity.notFound().build();
        }

        // Generate 6-digit random code
        String resetCode = userAccountService.generateResetCode();

        // Save the reset code in the user's record
        userAccount.setResetCode(resetCode);
        userAccountService.updateUserAccount(userAccount);

        // Send the reset code to the user's email
        emailService.sendResetCodeEmail(userAccount.getEmail(), resetCode);

        return ResponseEntity.ok("Reset code sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        UserAccount userAccount = userAccountService.getUserByEmail(resetPasswordDTO.getEmail());

        if (userAccount == null) {
            return ResponseEntity.notFound().build();
        }

        // Check if the reset code matches
        if (!userAccount.getResetCode().equals(resetPasswordDTO.getResetCode())) {
            return ResponseEntity.badRequest().body("Invalid reset code");
        }

        // Reset the password
        String hashedPassword = passwordService.hashPassword(resetPasswordDTO.getPassword());
        userAccount.setPassword(hashedPassword);
        userAccount.setResetCode(null);
        userAccountService.updateUserAccount(userAccount);

        return ResponseEntity.ok("Password reset successful");
    }

}
