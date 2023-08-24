package com.adaavanade.shoppingcart.dto;

public record UserDTO(
    Long id,
    String cpf,
    String name,
    String username,
    String email
) {
}

