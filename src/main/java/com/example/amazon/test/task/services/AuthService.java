package com.example.amazon.test.task.services;

import com.example.amazon.test.task.web.AuthenticationRequest;
import com.example.amazon.test.task.web.AuthenticationResponse;
import com.example.amazon.test.task.web.RegisterRequest;

public interface AuthService {
    AuthenticationResponse registerUser(RegisterRequest request);
    AuthenticationResponse loginUser(AuthenticationRequest request);
}
