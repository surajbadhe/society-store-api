package com.tvf.societystore.service;


import com.tvf.societystore.dto.auth.AuthResponse;
import com.tvf.societystore.dto.auth.LoginRequest;
import com.tvf.societystore.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}   