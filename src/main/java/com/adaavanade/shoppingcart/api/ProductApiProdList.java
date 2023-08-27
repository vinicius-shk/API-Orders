package com.adaavanade.shoppingcart.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductApiProdList {
  private String title;
  private String description;
  private Double price;
  private Integer stock;
}
