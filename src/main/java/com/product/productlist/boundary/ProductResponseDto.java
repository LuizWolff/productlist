package com.product.productlist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class ProductResponseDto {

    private Long id;
    private String descricao;

}
