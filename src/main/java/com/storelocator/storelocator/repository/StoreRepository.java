package com.storelocator.storelocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storelocator.storelocator.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}