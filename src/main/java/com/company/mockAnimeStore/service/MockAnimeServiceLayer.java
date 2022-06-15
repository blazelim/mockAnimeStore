package com.company.mockAnimeStore.service;

import com.company.mockAnimeStore.models.MockProjectRepository;
import com.company.mockAnimeStore.models.Order;
import com.company.mockAnimeStore.repository.EnrichedOrderRepository;
import com.company.mockAnimeStore.repository.OrderRepository;
import com.company.mockAnimeStore.repository.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class MockAnimeServiceLayer {
    private OrderRepository orderRepository;
    private EnrichedOrderRepository enrichedOrderRepository;
    private ProductRepository productRepository;

    public MockProjectRepository mockRepo = new MockProjectRepository();

    public MockAnimeServiceLayer(EnrichedOrderRepository enrichedOrderRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.enrichedOrderRepository = enrichedOrderRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Mono<Order> saveOrder(Order order) {
//
//        Mono<Product> product = productRepository.findByName(order.getProductName());

        return Mono.just(order)
                .flatMap(o -> productRepository.findByName(o.getProductName()))
                .flatMap(p -> {
                    order.setTotalPrice(order.getQuantity() * p.getPrice());
                    order.setProduct(p);
                    return enrichedOrderRepository.save(order);
                });
//                .doOnNext(savedOrder -> {
//                    savedOrder.setProduct(mockRepo.returnAProduct(savedOrder.getProductName()));
//                    savedOrder.setTotalPrice(savedOrder.getProduct().getPrice()*savedOrder.getQuantity());
//                })
//                .doOnNext(saveOrder -> {})
//                .flatMap(persistOrderToElastic(enrichedOrderRepository));
        //return enrichedOrder;
    }

    public static Function<Order, Mono<Order>> persistOrderToDB(OrderRepository repository){
        return order ->  repository.save(order);
    }

    public static Function<Order, Mono<Order>> persistOrderToElastic(EnrichedOrderRepository repository){
        return order -> repository.save(order);
    }
}
