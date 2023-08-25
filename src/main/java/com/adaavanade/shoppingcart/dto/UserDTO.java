package com.adaavanade.shoppingcart.dto;

public record UserDTO(
    Long id,
    String cpf,
    String name,
    String username,
    String email,
    String password
) {
  public UserDTO(Long id,String cpf,String name,String username,String email){
    this(id,cpf,name,username,email, null);
  }
}

