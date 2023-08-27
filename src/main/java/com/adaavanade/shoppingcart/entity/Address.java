package com.adaavanade.shoppingcart.entity;

import com.adaavanade.shoppingcart.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Enderecos")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String state;
  private String city;
  private String neighborhood;
  private String address;
  private String cep;

  public Address(UserDTO dto) {
    this.cep = dto.cep();
    this.state = dto.cep();
    this.city = dto.city();
    this.neighborhood = dto.neighborhood();
    this.address = dto.address();
  }
}