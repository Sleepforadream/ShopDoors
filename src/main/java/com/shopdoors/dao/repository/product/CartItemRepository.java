package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.Cart;
import com.shopdoors.dao.entity.product.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    void deleteAllByCart(Cart cart);
}
