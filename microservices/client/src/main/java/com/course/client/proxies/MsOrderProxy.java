package com.course.client.proxies;

import com.course.client.bean.OrderBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-order", url = "localhost:8093")
public interface MsOrderProxy {
    @PostMapping(value = "/order/{cartId}")
    ResponseEntity<OrderBean> createNewOrder(@PathVariable long cartId, @RequestBody Double total);
}
