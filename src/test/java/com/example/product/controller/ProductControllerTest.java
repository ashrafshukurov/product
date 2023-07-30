package com.example.product.controller;

import com.example.product.ProductApplication;
import com.example.product.dto.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ashraf on 27-Jul-23
 * @project product
 */
@SpringBootTest(classes = ProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@EnableJpaRepositories
class ProductControllerTest {
    @LocalServerPort
    private int port;

    private String url;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setup() {
        this.url = "http://localhost:" + port;
    }

    @Test
    @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenGetProductByIdWhenFoundThenReturnEntity() {
        //arrange
        Long id = 4L;
        ProductResponse productResponse = new ProductResponse();
        productResponse.setPrice(123);
        productResponse.setId(id);
        productResponse.setCount(2);

        //act
        ResponseEntity<ProductResponse> response = testRestTemplate.getForEntity(url + "/" + id, ProductResponse.class);

        //assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());



    }









}
