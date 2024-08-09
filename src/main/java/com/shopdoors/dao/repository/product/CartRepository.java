package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.Cart;
import com.shopdoors.dao.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser(User user);
}