package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public Product addNewProduct(Product product){
        Product newProduct = new Product();

        newProduct.setTitle(product.getTitle().toLowerCase());
        newProduct.setDescription(product.getDescription().toLowerCase());
        newProduct.setImage(product.getImage().toLowerCase());
        newProduct.setCategory(product.getCategory().toLowerCase());
        newProduct.setBrand(product.getBrand().toLowerCase());
        newProduct.setColour(product.getColour().toLowerCase());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());

        return productRepository.save(newProduct);
    }

    public Product updateProduct(Product updatedProduct){
        Product product = productRepository.getReferenceById(updatedProduct.getId());

        product.setTitle(updatedProduct.getTitle());
        product.setDescription(updatedProduct.getDescription());
        product.setImage(updatedProduct.getImage());
        product.setBrand(updatedProduct.getBrand());
        product.setColour(updatedProduct.getColour());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getAllProductsBySearchQuery(String query){
        List<Product> productList = productRepository.findAll();

        Set<Long> productIds = new HashSet<>();

        for(Product product : productList){
            if(product.getTitle().contains(query.toLowerCase())){
                productIds.add(product.getId());
            }
            if(product.getDescription().contains(query.toLowerCase())){
                productIds.add(product.getId());
            }
            if(product.getColour().contains(query.toLowerCase())){
                productIds.add(product.getId());
            }
            if(product.getBrand().contains(query.toLowerCase())){
                productIds.add(product.getId());
            }
        }

        return productRepository.findAllById(new ArrayList<>(productIds));
    }

    public List<Product> getAllProductsByPriceRange(double minPrice, double maxPrice){
        List<Product> products = productRepository.findAll();
        List<Long> productIds = new ArrayList<>();
        products.forEach(product -> {
            if(product.getPrice() >= minPrice && product.getPrice() <= maxPrice){
                productIds.add(product.getId());
            }
        });
        return productRepository.findAllById(productIds);
    }

    public List<Product> getAllProductsByCategory(String categoryType){
        List<Product> products = productRepository.findAll();
        List<Long> productIds = new ArrayList<>();
        products.forEach(product -> {
            if(product.getCategory().contains(categoryType.toLowerCase())){
                productIds.add(product.getId());
            }
        });
        return productRepository.findAllById(productIds);
    }

}
