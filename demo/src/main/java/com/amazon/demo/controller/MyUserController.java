package com.amazon.demo.controller;

import com.amazon.demo.model.MyUser;
import com.amazon.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/myusers")
public class MyUserController {

    @Autowired
    private MyUserService myUserService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MyUser myUser) {
        Optional<MyUser> existingUser = myUserService.findByEmail(myUser.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        myUserService.saveUser(myUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Optional<MyUser> user = myUserService.findByEmail(email);

        if (user.isPresent() && myUserService.checkPassword(user.get(), password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
}

