package com.adaavanade.shoppingcart.controller;

import com.adaavanade.shoppingcart.dto.ProductDTO;
import com.adaavanade.shoppingcart.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<ProductDTO> getAll() {
    return this.productService.getAll();
  }
}
