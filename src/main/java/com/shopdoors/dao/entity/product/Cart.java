package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Product;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.enums.product.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public void addItemOrUpdate(Product product, ProductType productType, int quantity) {
        CartItem existingItem = items.stream()
                .filter(item -> item.getProduct() != null &&
                        item.getProduct().getUuid().equals(product.getUuid()) &&
                        item.getProductType().equals(productType))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(this, product, quantity);
            newItem.setProductType(productType);
            items.add(newItem);
        }
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }
}