package com.adaavanade.shoppingcart.dto;

public record TokenDTO(String type, String token) {
  public TokenDTO(String token) {this("Bearer", token);}
}
