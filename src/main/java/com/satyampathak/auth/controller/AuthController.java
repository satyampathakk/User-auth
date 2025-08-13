package com.satyampathak.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.satyampathak.auth.dto.LoginRequest;
import com.satyampathak.auth.dto.RegisterRequest;
import com.satyampathak.auth.service.AuthService;


@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @GetMapping("/")
    public String getMethod() {
        return "Hello World!";
    }
    

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        String token = authService.login(req);
        return ResponseEntity.ok(Map.of("token", token));
    }
   
 @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestBody Map<String, String> req) {
        String token = req.get("token");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Token is required"));
        }
        boolean valid = authService.validateToken(token);
        String username=authService.getUserName(token);
        return ResponseEntity.ok(Map.of("valid", valid,"username",username));
    }
}