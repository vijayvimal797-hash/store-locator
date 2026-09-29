package com.storelocator.storelocator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storelocator.storelocator.model.Cart;
import com.storelocator.storelocator.model.Product;
import com.storelocator.storelocator.model.User;
import com.storelocator.storelocator.repository.CartRepository;
import com.storelocator.storelocator.repository.ProductRepository;
import com.storelocator.storelocator.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public String addToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if (user == null || product == null) return "Invalid user or product!";

        Optional<Cart> existing = cartRepository.findByUserIdAndProductId(userId, productId);
        if (existing.isPresent()) {
            Cart cart = existing.get();
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
            return "Quantity updated!";
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(1);
        cartRepository.save(cart);
        return "Added to cart!";
    }

    public List<Map<String, Object>> getCart(Long userId) {
        List<Cart> items = cartRepository.findByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Cart item : items) {
            Map<String, Object> map = new HashMap<>();
            map.put("cartId", item.getId());
            map.put("productId", item.getProduct().getId());
            map.put("productName", item.getProduct().getName());
            map.put("price", item.getProduct().getPrice());
            map.put("imageUrl", item.getProduct().getImageUrl());
            map.put("quantity", item.getQuantity());
            map.put("total", item.getProduct().getPrice() * item.getQuantity());
            result.add(map);
        }
        return result;
    }

    public String removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
        return "Removed from cart!";
    }

    public String updateQuantity(Long cartId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) return "Cart item not found!";
        if (quantity <= 0) {
            cartRepository.deleteById(cartId);
            return "Removed from cart!";
        }
        cart.setQuantity(quantity);
        cartRepository.save(cart);
        return "Quantity updated!";
    }
}