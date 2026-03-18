package com.oskar.ecommerce_api.service;

import com.oskar.ecommerce_api.entity.*;
import com.oskar.ecommerce_api.enums.OrderStatus;
import com.oskar.ecommerce_api.enums.PaymentStatus;
import com.oskar.ecommerce_api.repository.OrderRepository;
import com.oskar.ecommerce_api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentRepository paymentRepository;

    public Order checkout(Long userId) {
        Cart cart = cartService.getCartByUser(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho vazio");
        }

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem ->
                OrderItem.builder()
                        .product(cartItem.getProduct())
                        .quantity(cartItem.getQuantity())
                        .unitPrice(cartItem.getProduct().getPrice())
                        .build()
        ).collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));

        BigDecimal total = orderItems.stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .user(cart.getUser())
                .items(orderItems)
                .status(OrderStatus.PENDING)
                .total(total)
                .build();

        orderItems.forEach(i -> i.setOrder(order));
        Order saved = orderRepository.save(order);

        Payment payment = Payment.builder()
                .order(saved)
                .amount(total)
                .status(PaymentStatus.APPROVED)
                .processedAt(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);

        saved.setStatus(OrderStatus.CONFIRMED);
        cart.getItems().clear();

        return orderRepository.save(saved);
    }

    public List<Order> findByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}