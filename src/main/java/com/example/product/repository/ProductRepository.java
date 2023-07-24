package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
