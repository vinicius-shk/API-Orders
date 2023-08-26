package com.adaavanade.shoppingcart.filter;

import com.adaavanade.shoppingcart.entity.User;
import com.adaavanade.shoppingcart.service.AuthService;
import com.adaavanade.shoppingcart.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private JwtService jwtService;
  private AuthService authService;

  public AuthenticationFilter(JwtService jwtService, AuthService authService) {
    this.jwtService = jwtService;
    this.authService = authService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      String token = authHeader.replace("Bearer", "").trim();

      if (this.jwtService.validateJwt(token)) {
        String userName = this.jwtService.getUsernameFromJwt(token);
        User usuario = (User) this.authService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            usuario.getUsername(), null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
