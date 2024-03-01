package com.example.amazon.test.task.services;

import com.example.amazon.test.task.model.UserEntity;
import com.example.amazon.test.task.model.UserRepository;
import com.example.amazon.test.task.security.JwtService;
import com.example.amazon.test.task.web.AuthenticationRequest;
import com.example.amazon.test.task.web.AuthenticationResponse;
import com.example.amazon.test.task.web.RegisterRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerUser(RegisterRequest request) {
        var user = new UserEntity(request.getEmail(),
                passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = Optional.of(userRepository.findByUserEmail(request.getEmail())).orElseThrow(() -> new UsernameNotFoundException("Wrong email"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
