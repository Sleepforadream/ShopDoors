package com.shopdoors.dao.repository.order;

import com.shopdoors.dao.entity.order.Favorite;
import com.shopdoors.dao.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUser(User user);
}