package com.adaavanade.shoppingcart.service;

import com.adaavanade.shoppingcart.dto.LoginDTO;
import com.adaavanade.shoppingcart.dto.TokenDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  private AuthenticationManager authManager;
  private JwtService jwtService;

  public LoginService(AuthenticationManager authenticationManager, JwtService jwtService) {
    this.authManager = authenticationManager;
    this.jwtService = jwtService;
  }

  public TokenDTO login(LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

    authManager.authenticate(authToken);

    String jwtToken = jwtService.getJwtToken(loginDTO.username());
    return new TokenDTO(jwtToken);
  }
}
