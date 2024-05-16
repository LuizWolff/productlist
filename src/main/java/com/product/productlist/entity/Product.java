package com.product.productlist.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(name = "desc", nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double price;

    @Column
    private double rating;

    @Column(nullable = false)
    private int stock;

}
