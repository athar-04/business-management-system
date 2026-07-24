package com.athar.bms.auth.service;


import com.athar.bms.auth.dto.request.LoginRequest;
import com.athar.bms.auth.dto.request.RegisterRequest;
import com.athar.bms.auth.dto.response.AuthenticationResponse;

import com.athar.bms.business.repository.BusinessMemberRepository;
import com.athar.bms.business.repository.BusinessRepository;

import com.athar.bms.role.repository.RoleRepository;

//import com.athar.bms.security.jwt.JwtService;

import com.athar.bms.user.entity.User;
import com.athar.bms.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final BusinessRepository businessRepository;

    private final BusinessMemberRepository businessMemberRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    //private final JwtService jwtService;

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        return null;
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {

        // TODO

        return null;
    }

    private User createUser(RegisterRequest request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status("ACTIVE")
                .build();

        return userRepository.save(user);
    }
}