package com.product.productlist.boundary;

import com.product.productlist.control.DummyJsonApi;
import com.product.productlist.control.ProductService;
import com.product.productlist.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
// Com essa anotação a classe já consegue receber aquisições do POSTMAN/Front-END
// O Controller recebe as aquisições do front-end/clientes
public class ProductController {

    private final List<Product> productList;
    private final ModelMapper modelMapper;
    private final DummyJsonApi dummyJsonApi;
    private static Long CONTADOR = 2L;

    private final ProductService productService;


    public ProductController(List<Product> productList, ModelMapper modelMapper, DummyJsonApi dummyJsonApi, ProductService productService) {
        this.productList = productList;
        this.modelMapper = modelMapper;
        this.dummyJsonApi = dummyJsonApi;
        this.productService = productService;
    }

    @GetMapping("/products") //URI
    public List<ProductResponseDto> getProducts(){
        List<ProductResponseDto> productsResponseDto = productService.pegarProduct();

        return productsResponseDto;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductPorId(@PathVariable Long id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return modelMapper.map(product, ProductResponseDto.class);
            }
        }
        return null;
    }

    @GetMapping("/products/descricao")
    public List<Product> getProductPorDescricao(@RequestParam String desc) {
        List products = productList.stream().filter(product -> product.getDescricao().contains(desc)).toList();

        return products;
    }


    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto criarProducts(@Valid @RequestBody ProductRequestDto productRequestDto) {
      ProductResponseDto productDto = productService.saveProduct(productRequestDto);

        return productDto;

    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable @Valid @NotNull Long id){
        productService.apagarProductPorId(id);
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto editarProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto requestDto){

        ProductResponseDto productResponseDto = productService.editarProduct(id, requestDto);

        return productResponseDto;
    }

    @PatchMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarParcialmenteProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto){

        productService.editarParcilamente(id, requestDto);

    }

    @GetMapping("/dummyapi/products")//@RequestParam Long max, @RequestParam Long pulaItems
    public DummyProductResponse getProductsExternalApi(){

        DummyProductResponse products = dummyJsonApi.getProducts();

        return products;

    }

    @GetMapping("/dummyapi/products/{id}")
    public ProductResponseDto getProductExternalApiPorId(@PathVariable Long id){
        ProductItemResponse product = dummyJsonApi.getProductPorId(id);

        ProductResponseDto productDto = new ProductResponseDto();

        productDto.setId(product.getId());
        product.setTittle(product.getTittle());
        product.setDescription(product.getDescription());

        return productDto;

    }



}
