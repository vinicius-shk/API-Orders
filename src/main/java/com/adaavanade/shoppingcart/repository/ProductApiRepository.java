package com.adaavanade.shoppingcart.repository;


import com.adaavanade.shoppingcart.configuration.WebClientConfig;
import com.adaavanade.shoppingcart.api.ProductApiBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class ProductApiRepository {
  private WebClientConfig webClient;

  public ProductApiRepository(WebClientConfig webClient) {
    this.webClient = webClient;
  }

  public ProductApiBody getProductsApi() throws JsonProcessingException {
    return webClient.restTemplate().getForObject("https://dummyjson.com/products/search?q=phone", ProductApiBody.class);
  }
}
