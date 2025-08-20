package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.auth.AuthResponse;
import com.tvf.societystore.dto.auth.LoginRequest;
import com.tvf.societystore.dto.auth.RegisterRequest;
import com.tvf.societystore.entity.Role;
import com.tvf.societystore.entity.User;
import com.tvf.societystore.repository.UserRepository;
import com.tvf.societystore.security.JwtService;
import com.tvf.societystore.service.AuthService;
import com.tvf.societystore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.RESIDENT); // Default role
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(); // Exception handling can be improved here
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}
