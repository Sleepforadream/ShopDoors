package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.Baseboard;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseboardRepository extends JpaRepository<Baseboard, UUID> {
    Optional<Baseboard> findByName(String name);

    @Query("SELECT bb FROM Baseboard bb WHERE " +
            "(:fabric IS NULL OR bb.fabric = :fabric) AND " +
            "(:filling IS NULL OR bb.filling = :filling) AND " +
            "(:facing IS NULL OR bb.facing = :facing)")
    List<Baseboard> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("filling") Filling filling,
            @Param("facing") Facing facing,
            Sort sort);
}