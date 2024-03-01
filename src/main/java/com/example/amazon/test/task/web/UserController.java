package com.example.amazon.test.task.web;

import com.example.amazon.test.task.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Validated RegisterRequest request){
        return ResponseEntity.ok(authService.registerUser(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody @Validated AuthenticationRequest request){
        return ResponseEntity.ok(authService.loginUser(request));
    }
}
