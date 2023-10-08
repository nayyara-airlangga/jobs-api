package com.jobs.api.services;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobs.api.exceptions.InvalidAccessTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Autowired
    @Qualifier("jwt_secret_key")
    private String secretKey;

    private void verifyHeaders(JwsHeader header) {
        if (!header.getType().equals("JWT")) {
            throw new JwtException("Invalid JWT type");
        }
        if (!header.getAlgorithm().equals("HS256")) {
            throw new JwtException("Invalid algorithm");
        }
    }

    public Claims verifyToken(String token) {
        try {
            JwtParser parser = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build();
            Jws<Claims> jws = parser.parseSignedClaims(token);

            verifyHeaders(jws.getHeader());

            return jws.getPayload();
        } catch (JwtException e) {
            throw new InvalidAccessTokenException();
        }
    }

    public String generateToken(String sub, Map<String, Object> claims) {
        return Jwts.builder()
                .header().type("JWT")
                .and()
                .id(UUID.randomUUID().toString())
                .subject(sub)
                .issuer("jobs.api")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 3600L)) // One hour
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), Jwts.SIG.HS256)
                .claims(claims)
                .compact();
    }
}
