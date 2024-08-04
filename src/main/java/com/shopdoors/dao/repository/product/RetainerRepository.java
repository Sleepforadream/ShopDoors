package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.Retainer;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.KeyRetainer;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.Socket;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RetainerRepository extends JpaRepository<Retainer, Long> {
    Optional<Retainer> findByName(String name);

    @Query("SELECT dr FROM Retainer dr WHERE " +
            "(:fabric IS NULL OR dr.fabric = :fabric) AND " +
            "(:metal IS NULL OR dr.metal = :metal) AND " +
            "(:coating IS NULL OR dr.coating = :coating) AND " +
            "(:socket IS NULL OR dr.socket = :socket) AND " +
            "(:keyRetainer IS NULL OR dr.keyRetainer = :keyRetainer)")
    List<Retainer> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            @Param("socket") Socket socket,
            @Param("keyRetainer") KeyRetainer keyRetainer,
            Sort sort);
}