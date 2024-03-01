package com.example.amazon.test.task.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @Email(message = "Email pattern is not valid")
    private String email;
    @Size(min = 8, message = "password must contain 8 or more symbols")
    private String password;
}
