package com.tvf.societystore.service;

import com.tvf.societystore.dto.product.ProductDTO;

import java.util.List;

public interface ProductService  {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getAllProducts();
    void deleteProduct(Long productId);
}
