package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.Peephole;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.PeepholeType;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PeepholeRepository extends JpaRepository<Peephole, Long> {
    Optional<Peephole> findByName(String name);

    @Query("SELECT dp FROM Peephole dp WHERE " +
            "(:fabric IS NULL OR dp.fabric = :fabric) AND " +
            "(:metal IS NULL OR dp.metal = :metal) AND " +
            "(:coating IS NULL OR dp.coating = :coating) AND " +
            "(:minimumDepth IS NULL OR dp.minimumDepth = :minimumDepth) AND " +
            "(:maximumDepth IS NULL OR dp.maximumDepth = :maximumDepth) AND " +
            "(:peepholeType IS NULL OR dp.peepholeType = :peepholeType)")
    List<Peephole> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            @Param("minimumDepth") Integer minimumDepth,
            @Param("maximumDepth") Integer maximumDepth,
            @Param("peepholeType") PeepholeType peepholeType,
            Sort sort);
}