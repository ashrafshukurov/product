package com.example.product.service;

import com.example.product.dto.request.ProductRequest;
import com.example.product.dto.response.ProductResponse;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */

public interface ProductService {
     void createProduct(ProductRequest request);
     ProductResponse getProductById(Long id);
     boolean decreaseCountByCount(Long id,int count);
     void increaseCountByCount(Long id,int count);
}
