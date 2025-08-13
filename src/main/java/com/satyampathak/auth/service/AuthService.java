package com.satyampathak.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.satyampathak.auth.dto.LoginRequest;
import com.satyampathak.auth.dto.RegisterRequest;
import com.satyampathak.auth.entity.User;
import com.satyampathak.auth.repository.UserRepository;
import com.satyampathak.auth.security.JwtUtil;

@Service
public class AuthService {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    public String register(RegisterRequest req) {
        if (userRepo.findByUsername(req.username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(req.username);
        user.setPassword(encoder.encode(req.password));
        userRepo.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest req) {
        User user = userRepo.findByUsername(req.username)
            .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!encoder.matches(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }

     public boolean validateToken(String token){
        return jwtUtil.validateToken(token);
     }
     public String getUserName(String token){
        return jwtUtil.extractUsername(token);
     }
    
    
}