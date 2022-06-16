package com.company.mockAnimeStore.service;

import com.company.mockAnimeStore.models.EnrichedOrder;
import com.company.mockAnimeStore.models.MockProjectRepository;
import com.company.mockAnimeStore.models.Order;
import com.company.mockAnimeStore.repository.EnrichedOrderRepository;
import com.company.mockAnimeStore.repository.OrderRepository;
import com.company.mockAnimeStore.repository.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;


//TODO interface + impl
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

    public Mono<EnrichedOrder> saveOrder(final Order order) {

        return Mono.just(order)
                .flatMap(m -> orderRepository.save(m))
                .flatMap(o -> productRepository.findByName(o.getProductName()))
                .flatMap(p -> {
                    EnrichedOrder enrichedOrder = new EnrichedOrder(order);
                    enrichedOrder.setTotalPrice(order.getQuantity() * p.getPrice());
                    enrichedOrder.setProduct(p);
                    return enrichedOrderRepository.save(enrichedOrder);
                });

    }
//TODO Try optional.of(nullable), map/flatmap, flatmapiterable, final key word for unchangeable, Lombok annotated EnrichedOrder.builder(

    public Mono<EnrichedOrder> saveOrder2(final Order order) {

        return Optional.of(order).map(m -> orderRepository.save(m))
                .flatMap(o -> productRepository.findByName(o.getProductName()))
                .flatMap(p -> {
                    EnrichedOrder enrichedOrder = new EnrichedOrder(order);
                    enrichedOrder.setTotalPrice(order.getQuantity() * p.getPrice());
                    enrichedOrder.setProduct(p);
                    return enrichedOrderRepository.save(enrichedOrder);
                });

    }

    //TODO SaveOrder3 - take a list of orders -> Flux

    public static Function<Order, Mono<Order>> persistOrderToDB(OrderRepository repository){
        return order ->  repository.save(order);
    }


}
