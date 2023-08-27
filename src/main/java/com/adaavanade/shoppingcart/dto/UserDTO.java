package com.adaavanade.shoppingcart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserDTO(
    Long id,
    @CPF(message = "Invalid CPF") String cpf,
    @Size(min = 4, message = "Username must have between 4 and 20 characters", max = 20) String username,
    @Email(message = "Invalid email") String email,
    String password,
    String state,
    String city,
    String neighborhood,
    @Size(min = 2, message = "Address mus have between 2 and 200 characters", max = 200) String address,
    String cep
) {
  public UserDTO(Long id,String cpf, String username,String email, String state, String city, String neighborhood, String address, String cep){
    this(id, cpf, username,email, null, state, city, neighborhood, address, cep);
  }
}

