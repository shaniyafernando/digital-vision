package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.PlaceOrderDTO;
import com.DigitalVisionProject.service.models.*;
import com.DigitalVisionProject.service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;

    private final UserRepository userRepository;


    @Autowired
    public OrderService(AddressRepository addressRepository, ProductRepository productRepository,
                        OrderRepository orderRepository, OrderedProductRepository orderedProductRepository,
                        UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.userRepository = userRepository;
    }


    public PlaceOrderDTO addOrderDetails(Cart cart){
        Order newOrder = new Order();
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        cart.getCartItems().forEach(item ->{
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setProductId(item.getProductId());
            orderedProduct.setQuantityBought(item.getQuantityAddedToCart());
            OrderedProduct savedOrderedProduct = orderedProductRepository.save(orderedProduct);
            orderedProducts.add(savedOrderedProduct);


            Product product = productRepository.getReferenceById(item.getProductId());
            int updateInventory = product.getQuantity() - item.getQuantityAddedToCart();
            product.setQuantity(updateInventory);
        });

        newOrder.setOrderProducts(orderedProducts);
        newOrder.setSubTotal(cart.getTotal());
        newOrder.setDeliveryFee(20);

        List<User> users = userRepository.findAll();
        users.forEach(
                user -> {
                    if(user.getCartId().equals(cart.getId())){
                        newOrder.setUserId(user.getId());
                    }
                }
        );

        newOrder.setDate(LocalDate.now());
        Order order = orderRepository.save(newOrder);
        return new PlaceOrderDTO(order.getId(), cart.getId());
    }

    public Order getOrderById(Long id){
        return orderRepository.getReferenceById(id);
    }


    public Address updateDeliveryAddressOfOrder(Long id, String deliveryAddress){
        Address address = addressRepository.getReferenceById(id);
        address.setDeliveryAddress(deliveryAddress);
        return addressRepository.save(address);
    }

    public Address getAddress(Long userId){
        List<Address> addresses =  addressRepository.findAll();
        Address place = new Address();
        addresses.forEach(element -> {
            if(element.getUserId().equals(userId)){
                place.setId(element.getId());
                place.setUserId(element.getUserId());
                place.setDeliveryAddress(element.getDeliveryAddress());
                place.setBillingAddress(element.getBillingAddress());

            }
        });
        return place;
    }


}
