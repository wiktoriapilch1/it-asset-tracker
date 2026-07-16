package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.dto.auth.JwtResponseDTO;
import com.wiktoriapilch.itassettracker.dto.auth.LoginRequestDTO;
import com.wiktoriapilch.itassettracker.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    public final AuthenticationManager authenticationManager;
    public final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}
