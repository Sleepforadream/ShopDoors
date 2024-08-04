package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.Fastening;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FasteningRepository extends JpaRepository<Fastening, Long> {
    Optional<Fastening> findByName(String name);

    @Query("SELECT bb FROM Fastening bb WHERE " +
            "(:fabric IS NULL OR bb.fabric = :fabric) AND " +
            "(:metal IS NULL OR bb.metal = :metal) AND " +
            "(:coating IS NULL OR bb.coating = :coating)")
    List<Fastening> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            Sort sort);
}