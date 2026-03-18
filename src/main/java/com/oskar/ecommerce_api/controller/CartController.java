package com.oskar.ecommerce_api.controller;

import com.oskar.ecommerce_api.entity.Cart;
import com.oskar.ecommerce_api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/{userId}/items")
    public Cart addItem(@PathVariable Long userId,
                        @RequestParam Long productId,
                        @RequestParam Integer quantity) {
        return cartService.addItem(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public Cart removeItem(@PathVariable Long userId,
                           @PathVariable Long productId) {
        return cartService.removeItem(userId, productId);
    }
}