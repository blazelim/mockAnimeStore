package com.company.mockAnimeStore.api;

import com.company.mockAnimeStore.models.Order;
import com.company.mockAnimeStore.service.MockAnimeServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OrderController {

    private final MockAnimeServiceLayer service;

    public OrderController(MockAnimeServiceLayer service) {
        this.service = service;
    }

    @PostMapping(value="order",produces={APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Order> saveOrder(@RequestBody final Order order) {
        return service.saveOrder(order);
    };
}
