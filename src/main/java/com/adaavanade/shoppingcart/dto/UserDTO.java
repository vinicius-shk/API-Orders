package com.adaavanade.shoppingcart.dto;

public record UserDTO(
    Long id,
    String username,
    String email,
    String password,
    String state,
    String city,
    String neighborhood,
    String address,
    String cep
) {
  public UserDTO(Long id,String username,String email, String state, String city, String neighborhood, String address, String cep){
    this(id,username,email, null, state, city, neighborhood, address, cep);
  }
}

