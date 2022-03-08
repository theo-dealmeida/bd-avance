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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }
    @RequestMapping("/product-detail/{id}")
    public String product_detail(@PathVariable int id, Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("product", products.get(id));
        return "product_detail";
    }

    @RequestMapping("/cart/")
    public String cart(Model model) {
        ResponseEntity<CartBean> cart = msCartProxy.createNewCart(new CartBean((List<CartItemBean>) new CartItemBean(0L, 1)));
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/cart/{id}")
    public String cart(@PathVariable long id, Model model) {
        Optional<CartBean> cart = msCartProxy.getCart(id);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart/{id}")
    public String cart(@PathVariable int id, Model model) {
        model.addAttribute("cart", id);
        return "product_detail";
    }
}
