package com.course.client.controller;

import com.course.client.bean.CartBean;
import com.course.client.bean.CartItemBean;
import com.course.client.bean.OrderBean;
import com.course.client.bean.ProductBean;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsOrderProxy;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @Autowired
    private MsOrderProxy msOrderProxy;

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
        model.addAttribute("productId", id);
        model.addAttribute("item", null);
        model.addAttribute("cartId", CartId);
        model.addAttribute("quantity", 0);
        return "product_detail";
    }

    @GetMapping(value = "/cart")
    public RedirectView cart() {
        if (CartId == 0){
            ResponseEntity<CartBean> cart = msCartProxy.createNewCart();
            CartId = cart.getBody().getId();
        }
        return new RedirectView("cart/" + CartId);
    }

    @GetMapping("/cart/{id}")
    public String cart(@PathVariable long id, Model model) {
        if(id != CartId){
            model.addAttribute("cartMessage", "Tu ne peux pas voir ce cart");
            model.addAttribute("cart", null);
        }
        else{
            double priceTotal = 0.0;
            List<ProductBean> productsInCart = new ArrayList<>();
            Optional<CartBean> cart = msCartProxy.getCart(id);
            for (CartItemBean cartItemBean: cart.get().getProducts()) {
                Optional<ProductBean> productBean =  msProductProxy.get(cartItemBean.getProductId());
                if(productBean.isPresent()) {
                    productsInCart.add(productBean.get());
                    priceTotal += productBean.get().price * Double.valueOf(cartItemBean.getQuantity());
                }
            }
            model.addAttribute("cartMessage", "Voici ton cart");
            model.addAttribute("cartToString", cart.toString());
            model.addAttribute("cart", cart.get());
            model.addAttribute("priceTotal", priceTotal);
            model.addAttribute("productsInCart", productsInCart);
            model.addAttribute("cartId", cart.get().getId());
        }
        return "cartDetail";
    }

    @PostMapping("/cart/{idCart}/product/{idProduct}")
    public RedirectView cart(@PathVariable long idCart, @PathVariable long idProduct, @RequestBody Integer quantity, Model model) {
        if(CartId == 0){
            model.addAttribute("cartMessage", "Tu n'as pas encore de cart");
            model.addAttribute("cart", null);
        }
        else{
            CartItemBean cartItemBean = new CartItemBean(idProduct, quantity);
            msCartProxy.addProductToCart(idCart, cartItemBean);
            model.addAttribute("cartId", CartId);
        }
        return new RedirectView("/cart/" + CartId);
    }

    @GetMapping(value = "/order/{cartId}")
    public String order(@PathVariable long cartId, Model model) {
        Optional<CartBean> cart = msCartProxy.getCart(cartId);
        List<ProductBean> cartProducts = new ArrayList<>();
        double total = 0.00;
        for (CartItemBean cartItem : cart.get().getProducts()) {
                Optional<ProductBean> productBean = msProductProxy.get(cartItem.getProductId());
            if(productBean.isPresent()) {
                cartProducts.add(productBean.get());
                total += productBean.get().price * Double.valueOf(cartItem.getQuantity());
            }
        }
        ResponseEntity<OrderBean> order = msOrderProxy.createNewOrder(cartId, total);
        model.addAttribute("orderId", order.getBody().getId());
        model.addAttribute("orderMessage", "Order created");
        model.addAttribute("orderCart", cart);
        model.addAttribute("orderTotal", order.getBody().getTotal());
        model.addAttribute("orderProducts", cartProducts);

        return "order";
    }

    @RequestMapping("/order/done")
    public String orderDone() {
        return "orderDone";
    }
}
