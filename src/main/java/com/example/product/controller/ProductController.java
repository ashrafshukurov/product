package com.example.product.controller;

import com.example.product.dto.response.ProductResponse;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
//   @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> getProductByID(@PathVariable Long id){
//       return ResponseEntity.ok(productService.getProductById(id));
//   }
   @GetMapping("/dec/{id}/{amount}")
    public boolean decreaseCountByCount(@PathVariable Long id,int count){
      return productService.decreaseCountByCount(id,count);
   }
  @GetMapping("/inc/{id}/{amount}")
    public void increaseCountByCount(@PathVariable Long id,int count){
       productService.increaseCountByCount(id,count);
  }



}
