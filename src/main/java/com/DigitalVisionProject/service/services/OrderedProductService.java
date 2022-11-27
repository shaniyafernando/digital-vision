package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.models.OrderedProduct;
import com.DigitalVisionProject.service.repositories.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;

    @Autowired
    public OrderedProductService(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }

    public OrderedProduct addOrderedProduct(CartItem cartItem){
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setProduct(cartItem.getProduct());
        orderedProduct.setQuantityBought(cartItem.getQuantityAddedToCart());
        return orderedProductRepository.save(orderedProduct);
    }

    public void removeOrderedProduct(Long id){
        orderedProductRepository.deleteById(id);
    }


}
