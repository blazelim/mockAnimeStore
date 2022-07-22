package com.company.mockAnimeStore.service;

import com.company.mockAnimeStore.models.EnrichedOrder;
import com.company.mockAnimeStore.models.Order;
import com.company.mockAnimeStore.models.OrdersList;
import com.company.mockAnimeStore.repository.EnrichedOrderRepository;
import com.company.mockAnimeStore.repository.OrderRepository;
import com.company.mockAnimeStore.repository.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;


@Service
public class MockAnimeServiceLayerImpl implements MockAnimeServiceLayer{
    private OrderRepository orderRepository;
    private EnrichedOrderRepository enrichedOrderRepository;
    private ProductRepository productRepository;

    public MockAnimeServiceLayerImpl(EnrichedOrderRepository enrichedOrderRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.enrichedOrderRepository = enrichedOrderRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Mono<EnrichedOrder> saveOrder(final Order order) {
        return Mono.just(order)
                .flatMap(unsavedOrder -> orderRepository.save(unsavedOrder))
                .flatMap(savedOrder -> productRepository.findByName(savedOrder.getProductName()))
                .map(productItem -> {
                    return EnrichedOrder.builder()
                            .id(order.getId())
                            .productName(order.getProductName())
                            .quantity(order.getQuantity())
                            .product(productItem)
                            .totalPrice(productItem.getPrice()*order.getQuantity())
                            .build();
                })
                .flatMap(eOrder -> enrichedOrderRepository.save(eOrder));
    }

// map takes a Function <T, U> -> returns Flux <U>
// flatMap takes  a Function <T, Publisher<V> > -> returns Flux <V>

    // TODO Step Verifier make some tests, mockito

    @Override
    public Mono<EnrichedOrder> saveOrder2(final Order order) {
        return Mono.just(order)
                .flatMap(unsavedOrder -> orderRepository.save(unsavedOrder))
                .flatMap(processSingleOrder(productRepository))
                .flatMap(eOrder -> enrichedOrderRepository.save(eOrder));
    }


    @Override
    public Function<Order, Mono<EnrichedOrder>> processSingleOrder(ProductRepository productRepository) {

        return savedOrder -> Optional.of(savedOrder)
                .map(Order::getProductName)
                .map(productName -> productRepository.findByName(productName))
                .map(productItemMono ->
                        productItemMono.map(productItem ->
                                EnrichedOrder.builder()
                                    .id(savedOrder.getId())
                                    .productName(savedOrder.getProductName())
                                    .quantity(savedOrder.getQuantity())
                                    .product(productItem)
                                    .totalPrice(productItem.getPrice() * savedOrder.getQuantity())
                                    .build()
                ))
                .orElse(Mono.just(new EnrichedOrder(savedOrder)));
    }

    //SaveOrder3 - take a list of orders -> Flux

    @Override
    public Flux<EnrichedOrder> saveManyOrders(final OrdersList orderList) {

        return Flux.fromIterable(orderList.getOrders())
                .take(2)
                .flatMap(unsavedOrder -> orderRepository.save(unsavedOrder))
                .flatMap(processOrder(productRepository))
                .flatMap(eOrder -> enrichedOrderRepository.save(eOrder));
    };

    @Override
    public Function<Order, Mono<EnrichedOrder>> processOrder(ProductRepository repository){
        return savedOrder -> Mono.just(savedOrder)
                .map(Order::getProductName)
                .flatMap(productName -> productRepository.findByName(productName))
                .map(productItem -> {
                     return EnrichedOrder.builder()
                            .id(savedOrder.getId())
                            .productName(savedOrder.getProductName())
                            .quantity(savedOrder.getQuantity())
                            .product(productItem)
                            .totalPrice(productItem.getPrice()*savedOrder.getQuantity())
                            .build();
                });
    }
}





