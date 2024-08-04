package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.RoomLock;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.LockType;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.TongueType;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomLockRepository extends JpaRepository<RoomLock, Long> {
    Optional<RoomLock> findByName(String name);

    @Query("SELECT rl FROM RoomLock rl WHERE " +
            "(:fabric IS NULL OR rl.fabric = :fabric) AND " +
            "(:metal IS NULL OR rl.metal = :metal) AND " +
            "(:coating IS NULL OR rl.coating = :coating) AND " +
            "(:tongueType IS NULL OR rl.tongueType = :tongueType) AND " +
            "(:lockType IS NULL OR rl.lockType = :lockType)")
    List<RoomLock> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            @Param("tongueType") TongueType tongueType,
            @Param("lockType") LockType lockType,
            Sort sort);
}