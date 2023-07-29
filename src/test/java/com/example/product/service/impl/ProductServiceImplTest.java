package com.example.product.service.impl;

import com.example.product.dto.response.ProductResponse;
import com.example.product.exception.InSufficientCount;
import com.example.product.exception.NotFoundException;
import com.example.product.mapper.ProductMapper;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ashraf on 24-Jul-23
 * @project product
 */

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    public ProductServiceImpl productServiceImpl;

    @InjectMocks
    Product product;

    @BeforeEach
    void setUp(){
        product=new Product();
        product.setCount(2);
        product.setName("book");
        product.setPrice(4.50);
    }

    @Test
    public void givenGetProductByIdWhenFoundThenReturnResult(){
        //arrange
        Long productId=1L;
        product.setId(productId);
        setUp();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setId(productId);
        productResponse.setCount(2);
        productResponse.setPrice(4.50);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Mockito.when(mapper.entityToResponse(product)).thenReturn(productResponse);
        //act
        ProductResponse productResponse1= productServiceImpl.getProductById(productId);
        //assert
        assertEquals(4.50,productResponse1.getPrice());
        assertEquals(2,productResponse1.getCount());

    }

    @Test
    public void givenGetProductByIdWhenNotFoundThenThrowException404(){
        //arrange
        Long invalidId=100L;
        Mockito.when(productRepository.findById(invalidId)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class,()->productServiceImpl.getProductById(invalidId));
    }
    @Test
    public void givenDecreaseCountByCountWhenFoundThenReturnResult(){
        //arrange
        Long productId=1L;
        int decrease_count=5;
        int initial_count=10;
        Product product1=new Product();
        product1.setId(productId);
        product1.setCount(initial_count);//10
        product1.setName("book");
        product1.setPrice(4.50);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product1));
        //act
        boolean result=productServiceImpl.decreaseCountByCount(productId,decrease_count);//5
        //assert
        assertTrue(result);
        assertEquals(        initial_count-decrease_count,product1.getCount());
    }
    @Test
    public void givenDecreaseCountByCountWhenFoundInsufficientException(){
        //Arrange
        Long id=1L;
        int decrease_count=10;
        int initial_count=5;
        Product product1=new Product();
        product1.setPrice(4.50);
        product1.setCount(initial_count);
        product1.setId(id);
        product1.setName("book");
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product1));
        //Act&assert

        InSufficientCount inSufficientCount=assertThrows(InSufficientCount.class,()->productServiceImpl.decreaseCountByCount(id,decrease_count));
        assertEquals("Insufficient count",inSufficientCount.getMsg());

    }
    @Test
    public void givenDecreaseCountByCountWhenFoundProductException404(){
        //arrange
        Long id=1L;
        int decrease_count=10;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
        //assert & act

       NotFoundException exception= assertThrows(NotFoundException.class,()->productServiceImpl.decreaseCountByCount(id,decrease_count));
        assertEquals("Invalid product",exception.getMsg());

    }

    @Test
    public void givenIncreaseCountByCountWhenFoundThenReturnResult(){
        //arrange
        Long id=1L;
        int decrease_count=10;
        int initial_count=15;
        Product product1=new Product();
        product1.setName("book");
        product1.setCount(initial_count);
        product1.setPrice(4.50);
        product1.setId(id);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product1));

        //act

        productServiceImpl.increaseCountByCount(id,decrease_count);
        //assert

        assertEquals(initial_count+decrease_count,product1.getCount());

    }
    @Test
    public void givenIncreaseCountByCountWhenNotFoundThenThrowException404(){
        //arrange
        Long id=1L;
        int decrease_count=5;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
        //act & assert
        NotFoundException notFoundException=assertThrows(NotFoundException.class,()->productServiceImpl.increaseCountByCount(id,decrease_count));

        assertEquals("invalid product",notFoundException.getMsg());

    }



}