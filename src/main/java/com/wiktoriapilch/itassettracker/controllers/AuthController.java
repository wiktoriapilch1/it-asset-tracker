package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.dto.auth.JwtResponseDTO;
import com.wiktoriapilch.itassettracker.dto.auth.LoginRequestDTO;
import com.wiktoriapilch.itassettracker.dto.auth.RegisterRequestDTO;
import com.wiktoriapilch.itassettracker.dto.auth.RegisterResponseDTO;
import com.wiktoriapilch.itassettracker.exception.UserAlreadyExistsException;
import com.wiktoriapilch.itassettracker.models.employees.AppUser;
import com.wiktoriapilch.itassettracker.models.employees.RoleType;
import com.wiktoriapilch.itassettracker.repository.AppUserRepository;
import com.wiktoriapilch.itassettracker.services.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    public final AuthenticationManager authenticationManager;
    public final JwtService jwtService;
    public final AppUserRepository appUserRepository;
    public final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
        if(appUserRepository.existsByUsername(registerRequest.username())) {
            throw new UserAlreadyExistsException(ErrorMessages.USERNAME_ALREADY_EXISTS);
        }
        String encodedPass = this.passwordEncoder.encode(registerRequest.password());
        AppUser newUser = new AppUser();
        newUser.setUsername(registerRequest.username());
        newUser.setPassword(encodedPass);
        newUser.setRole(RoleType.EMPLOYEE);
        this.appUserRepository.save(newUser);
        return ResponseEntity.ok(new RegisterResponseDTO(newUser.getUsername(), newUser.getRole()));
    }
}
