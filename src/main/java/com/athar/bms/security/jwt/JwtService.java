package com.athar.bms.security.jwt;

import com.athar.bms.user.entity.User;

public interface JwtService {

    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);

}