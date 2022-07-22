package com.company.mockAnimeStore.service;

import com.company.mockAnimeStore.models.EnrichedOrder;
import com.company.mockAnimeStore.models.Order;
import com.company.mockAnimeStore.models.OrdersList;
import com.company.mockAnimeStore.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;


//TODO interface + impl

public interface MockAnimeServiceLayer {

    Mono<EnrichedOrder> saveOrder(Order order);

    Mono<EnrichedOrder> saveOrder2(Order order);

    Function<Order, Mono<EnrichedOrder>> processSingleOrder(ProductRepository productRepository);

    Flux<EnrichedOrder> saveManyOrders(OrdersList orderList);

    Function<Order, Mono<EnrichedOrder>> processOrder(ProductRepository repository);
}





