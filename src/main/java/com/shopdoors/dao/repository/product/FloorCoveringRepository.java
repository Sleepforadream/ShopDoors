package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.FloorCovering;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.WaterResistanceType;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FloorCoveringRepository extends JpaRepository<FloorCovering, UUID> {
    Optional<FloorCovering> findByName(String name);

    @Query("SELECT fl FROM FloorCovering fl WHERE " +
            "(:fabric IS NULL OR fl.fabric = :fabric) AND " +
            "(:filling IS NULL OR fl.filling = :filling) AND " +
            "(:facing IS NULL OR fl.facing = :facing) AND " +
            "(:waterResistanceType IS NULL OR fl.waterResistanceType = :waterResistanceType)")
    List<FloorCovering> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("filling") Filling filling,
            @Param("facing") Facing facing,
            @Param("waterResistanceType") WaterResistanceType waterResistanceType,
            Sort sort);
}