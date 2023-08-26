package com.adaavanade.shoppingcart.service;


import com.adaavanade.shoppingcart.entity.User;
import com.adaavanade.shoppingcart.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
  private UserRepository userRepository;

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = this.userRepository.findByUsername(username);
    if (user.isPresent()) {
      return user.get();
    }
    throw new UsernameNotFoundException("User not found!");
  }
}
