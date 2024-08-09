package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.door.RoomDoor;
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

public interface RoomDoorRepository extends JpaRepository<RoomDoor, UUID> {
    Optional<RoomDoor> findByName(String name);

    @Query("SELECT rd FROM RoomDoor rd WHERE " +
            "(:fabric IS NULL OR rd.fabric = :fabric) AND " +
            "(:facing IS NULL OR rd.facing = :facing) AND " +
            "(:filling IS NULL OR rd.filling = :filling)")
    List<RoomDoor> findAllWithFilters(@Param("fabric") Fabric fabric,
                                      @Param("facing") Facing facing,
                                      @Param("filling") Filling filling,
                                      Sort sort);
}