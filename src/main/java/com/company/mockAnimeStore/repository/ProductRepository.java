package com.company.mockAnimeStore.repository;

import com.company.mockAnimeStore.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

//public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Mono<Product> findByName(String productName);

//    @Query("SELECT * FROM customer WHERE last_name = :lastname")
//    public Product findByName(String name);
}
