package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long id);

}
