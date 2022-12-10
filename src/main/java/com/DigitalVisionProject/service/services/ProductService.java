package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.SearchDTO;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.enums.Category;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.DigitalVisionProject.service.models.enums.Category.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Category getCategoryType(String number){
        int i = Integer.parseInt(number);

        if(Category.STANDS.ordinal() == i){
            return Category.STANDS;
        }

        if(Category.AUTOMOBILE_GRADLE.ordinal() == i){
            return Category.AUTOMOBILE_GRADLE;
        }

        if(Category.GRIPS.ordinal() == i){
            return Category.GRIPS;
        }

        if(Category.HANDHELD_GIMBALS_AND_STABILIZERS.ordinal() == i){
            return Category.HANDHELD_GIMBALS_AND_STABILIZERS;
        }

        if(Category.SCREEN_EXPANDERS_AND_MAGNIFIERS.ordinal() == i){
            return Category.SCREEN_EXPANDERS_AND_MAGNIFIERS;
        }
        return Category.ALL;
    }

    public Product addNewProduct(Product product){
        Product newProduct = new Product();

        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        Category type = getCategoryType(product.getCategory());
        newProduct.setCategory(type.name());
        newProduct.setBrand(product.getBrand());
        newProduct.setColour(product.getColour());
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
        Category type = getCategoryType(updatedProduct.getCategory());
        product.setCategory(type.name());
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

    public List<Product> getAllProducts(String query){
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(product ->
            product.getTitle().equals(query) || product.getDescription().equals(query)
                    || product.getColour().equals(query) || product.getBrand().equals(query)
        ).collect(Collectors.toList());
    }


    public List<Product> filterAllProducts(SearchDTO searchDTO){
        List<Product> products = filterAllProductsByCategoryType(searchDTO.getType());
        return filterAllProductsByPriceRange(searchDTO.getMinPrice(), searchDTO.getMaxPrice(), products);
    }

    public List<Product> filterAllProductsByPriceRange(double minPrice, double maxPrice, List<Product> products){
        return products.stream().filter(product -> product.getPrice() <= maxPrice && product.getPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public List<Product> filterAllProductsByCategoryType(String categoryType){
        List<Product> products = productRepository.findAll();
        Category type = getCategoryType(categoryType);
        List<Product>  filteredProducts = products.stream().filter(product ->
                product.getCategory().contains(type.name())).toList();

        if(filteredProducts.isEmpty()){
            return products;
        }
        return filteredProducts;
    }

    public Product getProduct(Long id){
        return productRepository.getReferenceById(id);
    }

}
