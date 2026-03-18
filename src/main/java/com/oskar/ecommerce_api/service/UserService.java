package com.oskar.ecommerce_api.service;

import com.oskar.ecommerce_api.entity.Cart;
import com.oskar.ecommerce_api.entity.User;
import com.oskar.ecommerce_api.repository.CartRepository;
import com.oskar.ecommerce_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
    }

    public User save(User user) {
        User saved = userRepository.save(user);
        Cart cart = Cart.builder().user(saved).build();
        cartRepository.save(cart);
        return saved;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}