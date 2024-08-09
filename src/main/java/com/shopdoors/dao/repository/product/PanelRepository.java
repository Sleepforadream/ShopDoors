package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.Panel;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.PanelType;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PanelRepository extends JpaRepository<Panel, UUID> {
    Optional<Panel> findByName(String name);

    @Query("SELECT pn FROM Panel pn WHERE " +
            "(:fabric IS NULL OR pn.fabric = :fabric) AND " +
            "(:filling IS NULL OR pn.filling = :filling) AND " +
            "(:facing IS NULL OR pn.facing = :facing) AND " +
            "(:panelType IS NULL OR pn.panelType = :panelType)")
    List<Panel> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("filling") Filling filling,
            @Param("facing") Facing facing,
            @Param("panelType") PanelType panelType,
            Sort sort);
}