package com.shopdoors.service.product;

import com.shopdoors.dao.entity.abstracted.Product;
import com.shopdoors.dao.entity.product.molding.FloorCovering;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.WaterResistanceType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.FloorCoveringRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class FloorCoveringService implements ProductService {
    private final FloorCoveringRepository floorCoveringRepository;

    public List<FloorCovering> getFilteredFloorCoverings(
            String sortBy,
            String order,
            String fabric,
            String filling,
            String facing,
            String waterResistanceType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Filling fillingEnum = !Objects.equals(filling, null) && !Objects.equals(filling, "") ? Filling.valueOf(filling) : null;
        Facing facingEnum = !Objects.equals(facing, null) && !Objects.equals(facing, "") ? Facing.valueOf(facing) : null;
        WaterResistanceType waterResistanceTypeEnum = !Objects.equals(waterResistanceType, null) && !Objects.equals(waterResistanceType, "")
                        ? WaterResistanceType.valueOf(waterResistanceType) : null;

        return floorCoveringRepository.findAllWithFilters(fabricEnum, fillingEnum, facingEnum, waterResistanceTypeEnum, sort);
    }

    public FloorCovering getFloorCoveringById(UUID id) {
        return floorCoveringRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return floorCoveringRepository
                .findByName(name)
                .orElse(new FloorCovering())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return floorCoveringRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.FLOOR_COVERING;
    }
}
