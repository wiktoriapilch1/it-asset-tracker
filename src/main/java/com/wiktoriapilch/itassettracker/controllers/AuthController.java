package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.dto.auth.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(loginRequest.username());
    }
}
