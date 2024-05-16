package com.product.productlist.control;

import com.product.productlist.boundary.DummyProductResponse;
import com.product.productlist.boundary.ProductItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


    @FeignClient(name="productdummyjson", url ="https://dummyjson.com")
public interface DummyJsonApi {

    @GetMapping("/products")//@RequestParam Long limit, @RequestParam Long skip
    DummyProductResponse getProducts();

    @GetMapping("products/{id}")
    ProductItemResponse getProductPorId(@PathVariable Long id);
    


}
