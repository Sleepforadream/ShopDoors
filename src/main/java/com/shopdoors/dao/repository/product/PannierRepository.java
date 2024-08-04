package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.Pannier;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.MoldingType;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PannierRepository extends JpaRepository<Pannier, Long> {
    Optional<Pannier> findByName(String name);

    @Query("SELECT pn FROM Pannier pn WHERE " +
            "(:fabric IS NULL OR pn.fabric = :fabric) AND " +
            "(:filling IS NULL OR pn.filling = :filling) AND " +
            "(:facing IS NULL OR pn.facing = :facing) AND " +
            "(:moldingType IS NULL OR pn.moldingType = :moldingType)")
    List<Pannier> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("filling") Filling filling,
            @Param("facing") Facing facing,
            @Param("moldingType") MoldingType moldingType,
            Sort sort);
}