package com.storelocator.storelocator.service;

import com.storelocator.storelocator.model.Product;
import com.storelocator.storelocator.repository.ProductRepository;
import com.storelocator.storelocator.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Map<String, Object> getProductWithRating(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;

        Double avg = reviewRepository.findAverageRatingByProductId(id);
        Long count = reviewRepository.findReviewCountByProductId(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", product.getId());
        result.put("name", product.getName());
        result.put("description", product.getDescription());
        result.put("price", product.getPrice());
        result.put("category", product.getCategory());
        result.put("imageUrl", product.getImageUrl());
        result.put("averageRating", avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0);
        result.put("reviewCount", count != null ? count : 0);

        return result;
    }

    public List<Map<String, Object>> getAllProductsWithRatings() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> {
            Double avg = reviewRepository.findAverageRatingByProductId(p.getId());
            Long count = reviewRepository.findReviewCountByProductId(p.getId());

            Map<String, Object> result = new HashMap<>();
            result.put("id", p.getId());
            result.put("name", p.getName());
            result.put("description", p.getDescription());
            result.put("price", p.getPrice());
            result.put("category", p.getCategory());
            result.put("imageUrl", p.getImageUrl());
            result.put("averageRating", avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0);
            result.put("reviewCount", count != null ? count : 0);

            return result;
        }).toList();
    }
}