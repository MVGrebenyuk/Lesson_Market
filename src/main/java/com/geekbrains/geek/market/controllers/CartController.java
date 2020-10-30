package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.CartDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import com.geekbrains.geek.market.utils.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/add/{product_id}")
    public void addToCart(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
    }

    @GetMapping("/dec/{product_id}")
    public void decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
    }

    @GetMapping("/remove/{product_id}")
    public void removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
    }

    @GetMapping("/allProducts")
    public CartDto getCartDto() {
        cart.recalculate();
        return new CartDto(cart);
    }

    @GetMapping
    public CurrentUser getForFrontUser(Principal principal){
        CurrentUser user = new CurrentUser();
        user.setUsername(principal.getName());
        user.setId(userService.findByUsername(principal.getName()).getId());
        return user;
    }

    @PostMapping
    public void checkout(@RequestBody Order order){
        orderService.save(order);
        System.out.println("Добавлен новый заказ");
    }
}
