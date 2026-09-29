package com.storelocator.storelocator.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private String username;
    private Integer rating;
    private String comment;
    private LocalDateTime createdDate;
}