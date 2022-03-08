package com.course.client.controller;

import com.course.client.bean.ProductBean;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;
    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }
    @RequestMapping("/product-detail/{id}")
    public String product_detail( @PathVariable int id, Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("product", products.get(id));
        return "product_detail";
    }
}
