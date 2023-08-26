package com.adaavanade.shoppingcart.entity;

import com.adaavanade.shoppingcart.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String cpf;
  @Column(unique = true)
  private String username;
  private String email;
  private String password;
  @OneToOne
  @JoinColumn(name = "id_endereco")
  private Address address;

  public User(UserDTO dto) {
    this.cpf = dto.cpf();
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
        this.getUsername(),
        this.getEmail());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
