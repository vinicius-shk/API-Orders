package com.adaavanade.shoppingcart.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
  private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String getJwtToken(String username) {
    LocalDateTime issueDate = LocalDateTime.now();
    LocalDateTime expirationDate = issueDate.plusHours(2);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date(issueDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
        .setExpiration(new Date(expirationDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
        .signWith(secretKey)
        .compact();
  }

  public Boolean validateJwt (String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .isSigned(token);
  }

  public String getUsernameFromJwt (String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
