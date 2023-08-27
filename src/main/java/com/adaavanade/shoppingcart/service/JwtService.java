package com.adaavanade.shoppingcart.service;

import com.adaavanade.shoppingcart.entity.User;
import com.adaavanade.shoppingcart.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {
  private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private UserRepository userRepository;

  public JwtService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String getJwtToken(String username) {
    LocalDateTime issueDate = LocalDateTime.now();
    LocalDateTime expirationDate = issueDate.plusHours(2);
    Optional<User> user = userRepository.findByUsername(username);
    String id = user.get().getId().toString();

    return Jwts.builder()
        .setSubject(username)
        .setId(id)
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

  public Long getIdFromJwt (String token) {
    String id = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getId();

    return Long.parseLong(id);
  }
}
