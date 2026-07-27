package com.athar.bms.auth.service;


import com.athar.bms.auth.dto.request.LoginRequest;
import com.athar.bms.auth.dto.request.RegisterRequest;
import com.athar.bms.auth.dto.response.AuthenticationResponse;

import com.athar.bms.business.repository.BusinessMemberRepository;
import com.athar.bms.business.repository.BusinessRepository;

import com.athar.bms.role.repository.RoleRepository;

import com.athar.bms.security.jwt.JwtService;

import com.athar.bms.user.entity.User;
import com.athar.bms.user.repository.UserRepository;

import com.athar.bms.business.entity.Business;
import com.athar.bms.business.entity.BusinessMember;
import com.athar.bms.role.entity.Role;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = createUser(request);

        Business business = createBusiness(request);

        Role ownerRole = getOwnerRole();

        createBusinessMember(user, business, ownerRole);

        return AuthenticationResponse.builder()
                .message("Registration successful")
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
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

    private Business createBusiness(RegisterRequest request) {

        Business business = Business.builder()
                .businessName(request.getBusinessName())
                .build();

        return businessRepository.save(business);
    }

    private Role getOwnerRole() {

        return roleRepository.findByName("OWNER")
                .orElseThrow(() ->
                        new RuntimeException("OWNER role not found"));
    }

    private BusinessMember createBusinessMember(
            User user,
            Business business,
            Role role) {

        BusinessMember member = BusinessMember.builder()
                .user(user)
                .business(business)
                .role(role)
                .build();

        return businessMemberRepository.save(member);
    }
}