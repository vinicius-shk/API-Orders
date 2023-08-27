package com.adaavanade.shoppingcart.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductApiBody {
  @JsonProperty("products")
  private ProductApiProdList[] productResponse;
}
