package com.adaavanade.shoppingcart.service;

import com.adaavanade.shoppingcart.api.ProductApiBody;
import com.adaavanade.shoppingcart.api.ProductApiProdList;
import com.adaavanade.shoppingcart.dto.ProductDTO;
import com.adaavanade.shoppingcart.entity.Product;
import com.adaavanade.shoppingcart.repository.ProductApiRepository;
import com.adaavanade.shoppingcart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
  private ProductRepository productRepository;
  private ProductApiRepository productApiRepository;

  public ProductService(ProductRepository productRepository,
                        ProductApiRepository productApiRepository) {
    this.productRepository = productRepository;
    this.productApiRepository = productApiRepository;
  }

  public List<ProductDTO> getAll() {
    List<Product> prodList = productRepository.findAll();
    if (prodList.isEmpty()) {
      ProductApiBody prodListApi = getProductFromApi();
      if (prodListApi == null) {
        return List.of();
      }
      List<Product> prodListFromApi =
          Arrays.stream(prodListApi.getProductResponse()).map(Product::new).toList();

      productRepository.saveAll(prodListFromApi);

      return prodListFromApi.stream().map(Product::productDTO).toList();
    }
    return prodList.stream().map(Product::productDTO).toList();
  }

  private ProductApiBody getProductFromApi() {
    ProductApiBody productApiList;
    try {
      productApiList = this.productApiRepository.getProductsApi();
    } catch (Exception e) {
      e.printStackTrace();
      productApiList = null;
    }
    return productApiList;
  }
}
