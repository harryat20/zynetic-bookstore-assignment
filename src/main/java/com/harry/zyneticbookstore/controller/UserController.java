package com.harry.zyneticbookstore.controller;

import com.harry.zyneticbookstore.model.User;
import com.harry.zyneticbookstore.service.JwtService;
import com.harry.zyneticbookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "UserController", description = "Handles user registration and login.")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(
            summary = "User Signup",
            description = "Registers a new user and saves their credentials in the database."
    )
    @PostMapping("/signup")
    public User register(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }
    @Operation(
            summary = "User Login",
            description = "Authenticates a user and returns a JWT token if credentials are valid."
    )
    @PostMapping("/login")
    public String login(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        } else {
            return "Login Failed";
        }
    }
}
