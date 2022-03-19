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

    private long CartId = 0;

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
        model.addAttribute("cartId", CartId);
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
            model.addAttribute("cartId", CartId);
        }

        return "cart";
    }

    @GetMapping("/cart/{id}")
    public String cart(@PathVariable long id, Model model) {
        if(id != CartId){
            model.addAttribute("cartMessage", "Tu ne peux pas voir ce cart");
            model.addAttribute("cart", null);
        }
        else{
            model.addAttribute("cartMessage", "Voici ton cart");
            Optional<CartBean> cart = msCartProxy.getCart(id);
            model.addAttribute("cartToString", cart.toString());
        }
        return "cartDetail";
    }

    @PostMapping("/cart/{id}")
    public String cart(@PathVariable long id, CartItemBean cartItem, Model model) {
        if(CartId == 0){
            model.addAttribute("cartMessage", "Tu n'as pas encore de cart");
            model.addAttribute("cart", null);
        }
        else{
            msCartProxy.addProductToCart(id, cartItem);
            model.addAttribute("cartMessage", "Voici ton cart");
            Optional<CartBean> cart = msCartProxy.getCart(id);
            model.addAttribute("cartToString", cart.toString());
        }
        return "cartDetail";
    }
}
