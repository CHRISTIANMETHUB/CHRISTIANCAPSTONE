package com.ses.gateway.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${secret}")
    private String jwtSecret;


    public void validateToken(final String token) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
}

