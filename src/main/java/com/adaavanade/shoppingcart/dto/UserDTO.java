package com.adaavanade.shoppingcart.dto;

public record UserDTO(
    Long id,
    String cpf,
    String username,
    String email,
    String password
) {
  public UserDTO(Long id,String cpf,String username,String email){
    this(id,cpf,username,email, null);
  }
}

