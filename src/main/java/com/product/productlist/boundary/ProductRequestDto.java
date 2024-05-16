package com.product.productlist.boundary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {

    @NotBlank(message="Titulo é obrigatorio")
    private String title;

    @NotBlank(message ="Descrição é obrigatorio")
    private String descricao;

    @NotNull(message = "Price é obrigatorio")
    private Double price;

    @NotNull(message = "Rating é obrigatorio")
    private Double rating;

    @NotNull(message = "Stock é obrigatorio")
    private Integer stock;

}

