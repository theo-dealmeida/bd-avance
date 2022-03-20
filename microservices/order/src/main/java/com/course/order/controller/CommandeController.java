package com.course.order.controller;

import com.course.order.entity.Commande;
import com.course.order.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    CommandeRepository orderRepository;

    @PostMapping(value = "/order/{cartId}")
    public ResponseEntity<Commande> createNewOrder(@PathVariable Long cartId, @RequestBody Double total) {
        Commande order = orderRepository.save(new Commande(cartId, total));
        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create order");
        return new ResponseEntity<Commande>(order, HttpStatus.CREATED);
    }
}
