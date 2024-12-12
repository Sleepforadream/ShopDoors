package com.shopdoors.dao.repository.order;

import com.shopdoors.dao.entity.order.FavoriteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteItemRepository extends JpaRepository<FavoriteItem, Long> {
}