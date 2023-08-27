package com.adaavanade.shoppingcart.entity;

import com.adaavanade.shoppingcart.api.ProductApiProdList;
import com.adaavanade.shoppingcart.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String description;
  private Double price;
  private Integer stock;

  public Product(ProductDTO dto) {
    this.title = dto.title();
    this.description = dto.description();
    this.price = dto.price();
    this.stock = dto.stock();
  }

  public Product(ProductApiProdList dto) {
    this.title = dto.getTitle();
    this.description = dto.getDescription();
    this.price = dto.getPrice();
    this.stock = dto.getStock();
  }

  public Product(ProductDTO dto, Long id) {
    this(dto);
    this.id = id;
  }

  public ProductDTO productDTO() {
    return new ProductDTO(this.id, this.title, this.description, this.price, this.stock);
  }
}
