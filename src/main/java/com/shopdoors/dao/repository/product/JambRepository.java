package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.Jamb;
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

public interface JambRepository extends JpaRepository<Jamb, UUID> {
    Optional<Jamb> findByName(String name);

    @Query("SELECT jb FROM Jamb jb WHERE " +
            "(:fabric IS NULL OR jb.fabric = :fabric) AND " +
            "(:filling IS NULL OR jb.filling = :filling) AND " +
            "(:facing IS NULL OR jb.facing = :facing) AND " +
            "(:moldingType IS NULL OR jb.moldingType = :moldingType)")
    List<Jamb> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("filling") Filling filling,
            @Param("facing") Facing facing,
            @Param("moldingType") MoldingType moldingType,
            Sort sort);
}