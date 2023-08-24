package com.adaavanade.shoppingcart.entity;

import com.adaavanade.shoppingcart.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String cpf;
  private String name;
  private String username;
  private String email;
  private String password;
  @OneToOne
  @JoinColumn(name = "id_endereco")
  private Address address;

  public UserDTO UserDTO() {
    return new UserDTO(this.getId(),
        this.getCpf(),
        this.getName(),
        this.getUsername(),
        this.getEmail());
  }
}
