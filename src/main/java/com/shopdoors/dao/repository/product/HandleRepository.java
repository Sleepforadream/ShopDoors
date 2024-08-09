package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.Socket;
import com.shopdoors.dao.enums.user.Fabric;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HandleRepository extends JpaRepository<Handle, UUID> {
    Optional<Handle> findByName(String name);

    @Query("SELECT dh FROM Handle dh WHERE " +
            "(:fabric IS NULL OR dh.fabric = :fabric) AND " +
            "(:metal IS NULL OR dh.metal = :metal) AND " +
            "(:coating IS NULL OR dh.coating = :coating) AND " +
            "(:socket IS NULL OR dh.socket = :socket) AND " +
            "(:rodLength IS NULL OR dh.rodLength = :rodLength)")
    List<Handle> findAllWithFilters(
            @Param("fabric") Fabric fabric,
            @Param("metal") Metal metal,
            @Param("coating") Coating coating,
            @Param("socket") Socket socket,
            @Param("rodLength") Integer rodLength,
            Sort sort);
}