package com.course.order.controller;

import com.course.order.entity.Order;
import com.course.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @PostMapping(value = "/order")
    public ResponseEntity<Order> createNewOrder() {
        Order order = orderRepository.save(new Order());
        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create order");
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = "/order/{id}")
    public Optional<Order> getOrder(@PathVariable Long id)
    {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get order");
        return order;
    }
}
