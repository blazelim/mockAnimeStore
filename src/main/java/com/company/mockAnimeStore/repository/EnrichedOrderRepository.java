package com.company.mockAnimeStore.repository;

import com.company.mockAnimeStore.models.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrichedOrderRepository extends ReactiveMongoRepository<Order, String> {
}
