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
  @Column(unique = true)
  private String username;
  private String email;
  private String password;
  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_address")
  private Address address;

  public User(UserDTO dto) {
    this.address = new Address(dto);
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
        this.getUsername(),
        this.getEmail(),
        this.address.getState(),
        this.address.getCity(),
        this.address.getNeighborhood(),
        this.address.getAddress(),
        this.address.getCep());
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
