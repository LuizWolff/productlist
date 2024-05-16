package com.product.productlist.boundary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductItemResponse {

    private Long id;
    private String tittle;
    private String description;


}
