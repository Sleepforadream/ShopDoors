package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.product.furniture.RoomHinge;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.HingeType;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.RoomHingeRepository;
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
public class RoomHingeService implements ProductService {
    private final RoomHingeRepository roomHingeRepository;

    public List<RoomHinge> getFilteredHinges(
            String sortBy, String order, String fabric, String metal, String coating, Integer count, String hingeType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;
        HingeType hingeTypeEnum = !Objects.equals(hingeType, null) && !Objects.equals(hingeType, "") ? HingeType.valueOf(hingeType) : null;

        return roomHingeRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, count, hingeTypeEnum, sort);
    }

    public RoomHinge getHingeById(UUID id) {
        return roomHingeRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return roomHingeRepository
                .findByName(name)
                .orElse(new RoomHinge())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return roomHingeRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ROOM_HINGE;
    }
}
