package com.product.productlist.boundary;

import com.product.productlist.boundary.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DummyProductResponse {

    private List<ProductItem> products;

}
