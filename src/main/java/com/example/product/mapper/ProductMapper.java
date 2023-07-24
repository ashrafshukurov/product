package com.example.product.mapper;

import com.example.product.dto.request.ProductRequest;
import com.example.product.dto.response.ProductResponse;
import com.example.product.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product requestToEntity(ProductRequest request);

    ProductResponse entityToResponse(Product product);

}
