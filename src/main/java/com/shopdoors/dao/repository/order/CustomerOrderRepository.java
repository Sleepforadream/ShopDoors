package com.shopdoors.dao.repository.order;

import com.shopdoors.dao.entity.order.CustomerOrder;
import com.shopdoors.dao.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUser(User user);
}