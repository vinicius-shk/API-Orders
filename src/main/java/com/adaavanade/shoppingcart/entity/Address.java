package com.adaavanade.shoppingcart.entity;

import com.adaavanade.shoppingcart.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

  public AddressDTO enderecoDTO() {
    return new AddressDTO(this.getId(),
        this.getState(),
        this.getCity(),
        this.getNeighborhood(),
        this.getAddress(),
        this.getCep());
  }
}
