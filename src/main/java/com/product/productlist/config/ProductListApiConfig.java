package com.product.productlist.config;


import com.product.productlist.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductListApiConfig {// Serve para configurações iniciais do projeto

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public List<Product> ProductList(){
        List productList = new ArrayList();


        Product product = new Product(1L, "Miojo","Miojo da Monica de tomate",
                1,8.5,50);

        Product product2 = new Product(2L, "Macarrão","Massa de macarrão",
                4,7.5,35);

        productList.add(product);
        productList.add(product2);

        return productList;
    }
}
