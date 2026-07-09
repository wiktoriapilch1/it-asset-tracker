package com.wiktoriapilch.itassettracker.repository;

import com.wiktoriapilch.itassettracker.models.employees.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByUsername(String username);
}
