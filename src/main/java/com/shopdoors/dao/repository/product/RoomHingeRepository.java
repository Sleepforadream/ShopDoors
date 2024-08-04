package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.RoomHinge;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.HingeType;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomHingeRepository extends JpaRepository<RoomHinge, Long> {
    Optional<RoomHinge> findByName(String name);

    @Query("SELECT dh FROM RoomHinge dh WHERE " +
            "(:fabric IS NULL OR dh.fabric = :fabric) AND " +
            "(:metal IS NULL OR dh.metal = :metal) AND " +
            "(:coating IS NULL OR dh.coating = :coating) AND " +
            "(:count IS NULL OR dh.count = :count) AND " +
            "(:hingeType IS NULL OR dh.hingeType = :hingeType)")
    List<RoomHinge> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            @Param("count") Integer count,
            @Param("hingeType") HingeType hingeType,
            Sort sort);
}