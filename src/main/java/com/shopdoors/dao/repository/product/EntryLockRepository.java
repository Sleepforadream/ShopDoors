package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.EntryLock;
import com.shopdoors.dao.entity.product.furniture.RoomLock;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.DefenseClass;
import com.shopdoors.dao.enums.product.KeyType;
import com.shopdoors.dao.enums.product.LockType;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntryLockRepository extends JpaRepository<EntryLock, UUID> {
    Optional<EntryLock> findByName(String name);

    @Query("SELECT rl FROM EntryLock rl WHERE " +
            "(:fabric IS NULL OR rl.fabric = :fabric) AND " +
            "(:metal IS NULL OR rl.metal = :metal) AND " +
            "(:coating IS NULL OR rl.coating = :coating) AND " +
            "(:defenseClass IS NULL OR rl.defenseClass = :defenseClass) AND " +
            "(:firstKeyType IS NULL OR rl.firstKeyType = :firstKeyType) AND " +
            "(:secondKeyType IS NULL OR rl.secondKeyType = :secondKeyType)")
    List<EntryLock> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            @Param("defenseClass") DefenseClass defenseClass,
            @Param("firstKeyType") KeyType firstKeyType,
            @Param("secondKeyType") KeyType secondKeyType,
            Sort sort);
}