package com.springboot.blog.controller;

import com.springboot.blog.contracts.JwtAuthResponse;
import com.springboot.blog.contracts.LoginDto;
import com.springboot.blog.contracts.RegisterDto;
import com.springboot.blog.service.IAuthService;
import jakarta.annotation.security.PermitAll;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final Log logger = LogFactory.getLog(AuthController.class);
    private IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"login", "signIn"})
    @PermitAll()
    public ResponseEntity<JwtAuthResponse> Login(@RequestBody LoginDto loginDto) {

        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        logger.info("token " + jwtAuthResponse);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }

    @PostMapping(value = {"register", "signUp"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        String response = authService.register(registerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
