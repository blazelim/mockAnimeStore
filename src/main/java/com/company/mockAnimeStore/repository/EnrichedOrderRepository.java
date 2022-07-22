package com.company.mockAnimeStore.repository;

import com.company.mockAnimeStore.models.EnrichedOrder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrichedOrderRepository extends ReactiveMongoRepository<EnrichedOrder, String> {
}
