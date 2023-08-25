package com.adaavanade.shoppingcart.service;

import com.adaavanade.shoppingcart.dto.UserDTO;
import com.adaavanade.shoppingcart.entity.User;
import com.adaavanade.shoppingcart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDTO create(UserDTO dto) {
    User user = new User(dto);
    userRepository.save(user);
    return user.UserDTO();
  }

  public List<UserDTO> getAll() {
    return this.userRepository.findAll().stream().map(User::UserDTO).toList();
  }

  public UserDTO getById(Long id) {
    Optional<User> userOp = this.userRepository.findById(id);
    return userOp.map(User::UserDTO).orElse(null);
  }

  public UserDTO edit(UserDTO dto, Long id) {
    User user = new User(dto, id);
    userRepository.save(user);
    return user.UserDTO();
  }
  public void delete(Long id) {
    userRepository.deleteById(id);
  }
}
