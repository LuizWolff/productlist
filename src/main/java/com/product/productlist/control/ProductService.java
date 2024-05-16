package com.product.productlist.control;

import com.product.productlist.boundary.ProductRequestDto;
import com.product.productlist.boundary.ProductResponseDto;
import com.product.productlist.control.ProductRepository;
import com.product.productlist.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DummyJsonApi dummyJsonApi;

    public ProductService(ProductRepository productRepository, DummyJsonApi dummyJsonApi) {
        this.productRepository = productRepository;
        this.dummyJsonApi = dummyJsonApi;
    }

    public ProductResponseDto saveProduct(ProductRequestDto productDto){
        Product product = new Product();

        product.setTitle(productDto.getTitle());
        product.setDescricao(productDto.getDescricao());
        product.setPrice(productDto.getPrice());
        product.setRating(productDto.getRating());
        product.setStock(productDto.getStock());

       Product savedProduct = productRepository.save(product);

       ProductResponseDto responseDto = new ProductResponseDto(savedProduct.getId(), savedProduct.getDescricao());

        return responseDto;
    }

    public List<ProductResponseDto> pegarProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponseDtos  = products.stream().map(product ->
                new ProductResponseDto(product.getId(), product.getDescricao())).toList();

        return productResponseDtos;
    }

    public void apagarProductPorId(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponseDto editarProduct(Long id, ProductRequestDto requestDto) {

        Optional<Product> productEncontrado = productRepository.findById(id);

        if(productEncontrado.isPresent()){
            Product product = productEncontrado.get();

            product.setTitle(requestDto.getTitle());
            product.setDescricao(requestDto.getDescricao());
            product.setPrice(requestDto.getPrice());
            product.setStock(requestDto.getStock());
            product.setRating(requestDto.getRating());

            Product productEditado = productRepository.save(product);

            return new ProductResponseDto(productEditado.getId(), productEditado.getDescricao());
        }

        return null;
    }

    public void editarParcilamente(Long id, ProductRequestDto requestDto) {
        Optional<Product> productEncontrado = productRepository.findById(id);

        if(productEncontrado.isPresent()){
            Product product = productEncontrado.get();

            if(requestDto.getTitle() != null) {
                product.setTitle(requestDto.getTitle());
            }

            if(requestDto.getDescricao() != null) {
                product.setDescricao(requestDto.getDescricao());
            }

            if (requestDto.getPrice() != null ) {
                product.setPrice(requestDto.getPrice());
            }

            if (requestDto.getStock() != null) {
                product.setStock(requestDto.getStock());
            }

            if (requestDto.getRating() != null) {
                product.setRating(requestDto.getRating());
            }

            Product productEditado = productRepository.save(product);
        }
    }

}
