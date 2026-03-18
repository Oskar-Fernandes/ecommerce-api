package com.oskar.ecommerce_api.service;

import com.oskar.ecommerce_api.entity.Cart;
import com.oskar.ecommerce_api.entity.CartItem;
import com.oskar.ecommerce_api.entity.Product;
import com.oskar.ecommerce_api.repository.CartRepository;
import com.oskar.ecommerce_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }

    public Cart addItem(Long userId, Long productId, Integer quantity) {
        Cart cart = getCartByUser(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + quantity),
                        () -> cart.getItems().add(
                                CartItem.builder().cart(cart).product(product).quantity(quantity).build()
                        )
                );

        return cartRepository.save(cart);
    }

    public Cart removeItem(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }
}