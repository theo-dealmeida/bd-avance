package com.course.client.controller;

import com.course.client.bean.CartBean;
import com.course.client.bean.CartItemBean;
import com.course.client.bean.ProductBean;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    private int CartId = 0;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/product-detail/{id}")
    public String product_details(@PathVariable long id, Model model) {
        Optional<ProductBean> product = msProductProxy.get(id);
        model.addAttribute("product", product);
        model.addAttribute("item", null);
        return "product_detail";
    }

    @GetMapping(value = "/cart")
    public String cart(Model model) {
        if (CartId == 0){
            ResponseEntity<CartBean> cart = msCartProxy.createNewCart();
            model.addAttribute("cartId", cart.getBody().getId());
            model.addAttribute("cartMessage", "Cart created");
            CartId = cart.getBody().getId();
        }
        else {
            model.addAttribute("cartMessage", "You already have a cart");
            model.addAttribute("CartId", CartId);
        }

        return "cart";
    }

    @GetMapping("/cart/{id}")
    public String cart(@PathVariable long id, Model model) {
        Optional<CartBean> cart = msCartProxy.getCart(id);
        model.addAttribute("cart", cart);
        return "cartDetail";
    }

    @PostMapping("/cart/{id}")
    public void cart(@PathVariable long id, CartItemBean cartItem) {
        msCartProxy.addProductToCart(id, cartItem);
    }
}
