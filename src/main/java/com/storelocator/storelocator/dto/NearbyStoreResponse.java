package com.storelocator.storelocator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearbyStoreResponse {
    private Long storeId;
    private String storeName;
    private String address;
    private String city;
    private String phone;
    private String openingTime;
    private String closingTime;
    private Double distanceKm;
    private Integer stockQuantity;
    private String status;
    private Double latitude;
    private Double longitude;
}