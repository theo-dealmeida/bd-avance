package com.course.client.proxies;

import com.course.client.bean.CartBean;
import com.course.client.bean.CartItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "ms-cart", url = "localhost:8092")
public interface MsCartProxy {
    @PostMapping(value = "/cart")
    ResponseEntity<CartBean> createNewCart();
    @GetMapping(value = "/cart/{id}")
    Optional<CartBean> getCart(@PathVariable long id);
    @PostMapping(value = "/cart/{id}")
    ResponseEntity<CartItemBean> addProductToCart(@PathVariable Long id, @RequestBody CartItemBean cartItem);
    @GetMapping(value = "/wipe/{id}")
    ResponseEntity<Boolean> wipeCart(@PathVariable Long id);
}
