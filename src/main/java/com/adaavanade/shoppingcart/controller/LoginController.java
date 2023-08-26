package com.adaavanade.shoppingcart.controller;

import com.adaavanade.shoppingcart.dto.LoginDTO;
import com.adaavanade.shoppingcart.dto.TokenDTO;
import com.adaavanade.shoppingcart.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
  private LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  private TokenDTO login(@RequestBody LoginDTO body) {
    return this.loginService.login(body);
  }
}
