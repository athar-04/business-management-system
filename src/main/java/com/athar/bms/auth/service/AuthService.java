package com.athar.bms.auth.service;

import com.athar.bms.auth.dto.request.LoginRequest;
import com.athar.bms.auth.dto.request.RegisterRequest;
import com.athar.bms.auth.dto.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);

}