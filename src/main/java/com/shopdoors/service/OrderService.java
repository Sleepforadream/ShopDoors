package com.shopdoors.service;

import com.shopdoors.dao.entity.order.CustomerOrder;
import com.shopdoors.dao.entity.order.CustomerOrderItem;
import com.shopdoors.dao.entity.product.Cart;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.repository.order.CustomerOrderRepository;
import com.shopdoors.dao.repository.product.CartItemRepository;
import com.shopdoors.dao.repository.product.CartRepository;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final TransactionRunner transactionRunner;

    public CustomerOrder createOrder(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setDeliveryDate(LocalDate.now().plusDays(35));
        order.setTotalPrice(cart.getTotalPrice());

        List<CustomerOrderItem> customerOrderItems = cart.getItems().stream()
            .map(cartItem -> {
                CustomerOrderItem customerOrderItem = new CustomerOrderItem();
                customerOrderItem.setCustomerOrder(order);
                customerOrderItem.setProduct(cartItem.getProduct());
                customerOrderItem.setQuantity(cartItem.getQuantity());
                customerOrderItem.setPrice(cartItem.getProduct().getPrice());
                return customerOrderItem;
            }).collect(Collectors.toList());

        order.setCustomerOrderItems(customerOrderItems);

        return transactionRunner.doInTransaction(() -> {
            cartItemRepository.deleteAllByCart(cart);
            return customerOrderRepository.save(order);
        });
    }

    public List<CustomerOrder> getOrderHistory(User user) {
        return customerOrderRepository.findByUser(user);
    }

    public CustomerOrder getOrderById(Long id) {
        return customerOrderRepository.findById(id).orElseThrow();
    }
}