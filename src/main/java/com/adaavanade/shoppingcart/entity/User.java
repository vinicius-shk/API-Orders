package com.adaavanade.shoppingcart.entity;

import com.adaavanade.shoppingcart.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

  public User(UserDTO dto) {
    this.cpf = dto.cpf();
    this.name = dto.name();
    this.username = dto.username();
    this.email = dto.email();
    this.password = dto.password();
  }

  public User(UserDTO dto, Long id) {
    this(dto);
    this.id = id;
  }

  public UserDTO UserDTO() {
    return new UserDTO(this.getId(),
        this.getCpf(),
        this.getName(),
        this.getUsername(),
        this.getEmail());
  }
}
