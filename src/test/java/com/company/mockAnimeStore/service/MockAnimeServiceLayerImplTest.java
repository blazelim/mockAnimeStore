package com.company.mockAnimeStore.service;

import com.company.mockAnimeStore.api.OrderController;
import com.company.mockAnimeStore.models.EnrichedOrder;
import com.company.mockAnimeStore.models.Order;
import com.company.mockAnimeStore.models.Product;
import com.company.mockAnimeStore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class MockAnimeServiceLayerImplTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private MockAnimeServiceLayer mockAnimeServiceLayer;

    private Product product;

    private Order order;

    private EnrichedOrder enrichedOrder;

    @BeforeEach
    public void setUp() {
        product = new Product("1", "fake Product", 1.11, 1,1,1);

        order = Order.builder()
                .productName("fake Product")
                .quantity(1)
                .build();

        enrichedOrder = EnrichedOrder.builder()
                .id("1")
                .productName("fake Product")
                .quantity(1)
                .totalPrice(1.11)
                .product(product)
                .build();
    }

    @Test
    public void saveOrder() {
        when(mockAnimeServiceLayer.saveOrder(order).thenReturn(Mono.just(enrichedOrder)));
        StepVerifier.create(orderController.saveOrder(order))
                .thenRequest(1)
                .expectNext(enrichedOrder)
                .verifyComplete();
    }
}