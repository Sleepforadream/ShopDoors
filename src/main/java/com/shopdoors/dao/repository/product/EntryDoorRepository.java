package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.door.EntryDoor;
import com.shopdoors.dao.entity.product.door.RoomDoor;
import com.shopdoors.dao.enums.product.Color;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EntryDoorRepository extends JpaRepository<EntryDoor, Long> {

    Optional<EntryDoor> findByName(String name);

    @Query("SELECT ed FROM EntryDoor ed WHERE " +
            "(:fabric IS NULL OR ed.fabric = :fabric) AND " +
            "(:metal IS NULL OR ed.metal = :metal) AND " +
            "(:color IS NULL OR ed.color = :color) AND " +
            "(:filling IS NULL OR ed.filling = :filling)")
    List<EntryDoor> findAllWithFilters(@Param("fabric") Fabric fabric,
                                      @Param("metal") Metal metal,
                                      @Param("color") Color color,
                                      @Param("filling") Filling filling,
                                      Sort sort);
}