package com.example.product.service.impl;

import com.example.product.dto.request.ProductRequest;
import com.example.product.dto.response.ProductResponse;
import com.example.product.exception.InSufficientCount;
import com.example.product.exception.NotFoundException;
import com.example.product.mapper.ProductMapper;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public void createProduct(ProductRequest request) {
        Product product=mapper.requestToEntity(request);
        log.info("Product:"+product.getName()+" is creating");
        repository.save(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("product with "+id+"th is searching");
      Product product= repository.findById(id).orElseThrow(()-> new NotFoundException("invalid product"));
      return mapper.entityToResponse(product);
    }

    @Override
    public boolean decreaseCountByCount(Long id,int count) {
        Product product= repository.findById(id).orElseThrow(()-> new NotFoundException("invalid product"));
         if(count>product.getCount()){
             log.warn("insufficient count");
             throw new InSufficientCount("Insufficient count");
         }
         product.setCount(product.getCount()-count);
         repository.save(product);
         return true;
    }

    @Override
    public void increaseCountByCount(Long id, int count) {
        Product product= repository.findById(id).orElseThrow(()-> new NotFoundException("invalid product"));
         product.setCount(product.getCount()+count);
         repository.save(product);
    }
}
