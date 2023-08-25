package com.adaavanade.shoppingcart.controller;

import com.adaavanade.shoppingcart.dto.UserDTO;
import com.adaavanade.shoppingcart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDTO create (@RequestBody UserDTO body) {
    return this.userService.create(body);
  }

  @GetMapping
  public List<UserDTO> getAll() {
    return this.userService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
    UserDTO user = this.userService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}")
  public UserDTO edit(@PathVariable Long id, @RequestBody UserDTO body) {
    return this.userService.edit(body, id);
    }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }
}
