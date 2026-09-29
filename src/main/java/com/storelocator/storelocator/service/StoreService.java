package com.storelocator.storelocator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storelocator.storelocator.dto.NearbyStoreResponse;
import com.storelocator.storelocator.model.Store;
import com.storelocator.storelocator.model.StoreInventory;
import com.storelocator.storelocator.repository.StoreInventoryRepository;
import com.storelocator.storelocator.repository.StoreRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreInventoryRepository storeInventoryRepository;

    // Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<NearbyStoreResponse> getNearbyStores(Long productId, double userLat, double userLng, double radiusKm) {
        List<StoreInventory> inventories = storeInventoryRepository.findByProductId(productId);
        List<NearbyStoreResponse> result = new ArrayList<>();

        for (StoreInventory inventory : inventories) {
            Store store = inventory.getStore();
            double distance = calculateDistance(userLat, userLng, store.getLatitude(), store.getLongitude());

            if (distance <= radiusKm) {
                NearbyStoreResponse response = new NearbyStoreResponse(
                    store.getId(),
                    store.getName(),
                    store.getAddress(),
                    store.getCity(),
                    store.getPhone(),
                    store.getOpeningTime(),
                    store.getClosingTime(),
                    Math.round(distance * 10.0) / 10.0,
                    inventory.getStockQuantity(),
                    inventory.getStatus(),
                    store.getLatitude(),
                    store.getLongitude()
                );
                result.add(response);
            }
        }
        return result;
    }
}