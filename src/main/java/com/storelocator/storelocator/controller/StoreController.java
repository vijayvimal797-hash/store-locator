package com.storelocator.storelocator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.storelocator.storelocator.dto.NearbyStoreResponse;
import com.storelocator.storelocator.service.StoreService;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "*")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/nearby")
    public List<NearbyStoreResponse> getNearbyStores(
            @RequestParam Long productId,
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "50") double radius) {

        return storeService.getNearbyStores(productId, lat, lng, radius);
    }
}