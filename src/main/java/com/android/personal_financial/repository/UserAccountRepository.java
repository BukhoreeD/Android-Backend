package com.android.personal_financial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.personal_financial.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    // custom query methods
    boolean existsByUsername(String username);

    Optional<UserAccount> findByUsernameAndPassword(String username, String password);

    UserAccount findByUsername(String username);
}

