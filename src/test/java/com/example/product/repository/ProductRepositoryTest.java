package com.example.product.repository;

import com.example.product.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ashraf on 28-Jul-23
 * @project product
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@EnableConfigurationProperties
@EnableJpaRepositories
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    @Sql(scripts = "classpath:sql/product.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenFindByIdWhenFoundThenReturnEntity(){
        //arrange
        Long id=4L;

        //act
        Optional<Product> result=productRepository.findById(id);

        //assert
        assertFalse(result.isEmpty());
        Product product=result.get();
        assertEquals(id,product.getId());
        assertEquals("pen",product.getName());
    }


}