package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.AdditionalElement;
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
import java.util.UUID;

public interface AdditionalElementRepository extends JpaRepository<AdditionalElement, UUID> {
    Optional<AdditionalElement> findByName(String name);

    @Query("SELECT ae FROM AdditionalElement ae WHERE " +
            "(:fabric IS NULL OR ae.fabric = :fabric) AND " +
            "(:filling IS NULL OR ae.filling = :filling) AND " +
            "(:facing IS NULL OR ae.facing = :facing) AND " +
            "(:moldingType IS NULL OR ae.moldingType = :moldingType)")
    List<AdditionalElement> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("filling") Filling filling,
            @Param("facing") Facing facing,
            @Param("moldingType") MoldingType moldingType,
            Sort sort);
}