package com.adaavanade.shoppingcart.dto;

public record AddressDTO(
    Long id,
    String state,
    String city,
    String neighborhood,
    String address,
    String cep
) {
}
