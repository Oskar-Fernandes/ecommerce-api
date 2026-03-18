package com.oskar.ecommerce_api.repository;

import com.oskar.ecommerce_api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}