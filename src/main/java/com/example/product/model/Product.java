package com.example.product.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ashraf on 18-Jul-23
 * @project product
 */

@Entity
@Data

@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int count;
    private double price;


}
