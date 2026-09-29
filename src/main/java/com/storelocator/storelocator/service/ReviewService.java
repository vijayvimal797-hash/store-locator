package com.storelocator.storelocator.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storelocator.storelocator.dto.ReviewRequest;
import com.storelocator.storelocator.dto.ReviewResponse;
import com.storelocator.storelocator.model.Product;
import com.storelocator.storelocator.model.Review;
import com.storelocator.storelocator.model.User;
import com.storelocator.storelocator.repository.ProductRepository;
import com.storelocator.storelocator.repository.ReviewRepository;
import com.storelocator.storelocator.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public String addReview(ReviewRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElse(null);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (product == null || user == null) return "Invalid product or user!";

        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedDate(LocalDateTime.now());
        reviewRepository.save(review);
        return "Review added!";
    }

    public List<ReviewResponse> getReviews(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(r -> new ReviewResponse(
                        r.getId(),
                        r.getUser().getUsername(),
                        r.getRating(),
                        r.getComment(),
                        r.getCreatedDate()
                ))
                .collect(Collectors.toList());
    }
}