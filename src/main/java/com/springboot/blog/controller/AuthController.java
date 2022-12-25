package com.springboot.blog.controller;

import com.springboot.blog.contracts.LoginDto;
import com.springboot.blog.contracts.RegisterDto;
import com.springboot.blog.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"login", "signIn"})
    public ResponseEntity<String> Login(@RequestBody LoginDto loginDto) {

        String response = authService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = {"register", "signUp"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        String response = authService.register(registerDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
