package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public  ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addNewProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping()
    public  ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product newProduct = productService.updateProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") @RequestBody Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/search")
    public  ResponseEntity<List<Product>> getAllProductsBySearchQuery(@RequestBody String query){
        List<Product> products = productService.getAllProductsBySearchQuery(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/all/price-range")
    public  ResponseEntity<List<Product>> getAllProductsByPriceRange(@RequestBody double minPrice, double maxPrice){
        List<Product> products = productService.getAllProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/all/category-type")
    public  ResponseEntity<List<Product>> getAllProductsByCategory(@RequestBody String category){
        List<Product> products = productService.getAllProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
