package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.SearchDTO;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public  ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        Product newProduct = productService.addNewProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping()
    public  ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product newProduct = productService.updateProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{type}/{minPrice}/{maxPrice}")
    public  ResponseEntity<List<Product>> filterAllProducts(
            @PathVariable("type") String type, @PathVariable("minPrice") double minPrice,@PathVariable("maxPrice") double maxPrice ){
        SearchDTO searchDTO = new SearchDTO(minPrice, maxPrice, type);
        List<Product> products = productService.filterAllProducts(searchDTO);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/all/{query}")
    public  ResponseEntity<List<Product>> filterAllProducts( @PathVariable("query") String query ){
        List<Product> products = productService.getAllProducts(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/all")
    public  ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
