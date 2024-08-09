package com.shopdoors.service.product;

import com.shopdoors.dao.entity.abstracted.Product;
import com.shopdoors.dao.entity.product.molding.Baseboard;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.BaseboardRepository;
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
public class BaseboardService implements ProductService {
    private final BaseboardRepository baseboardRepository;

    public List<Baseboard> getFilteredBaseboards(
            String sortBy,
            String order,
            String fabric,
            String filling,
            String facing
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Filling fillingEnum = !Objects.equals(filling, null) && !Objects.equals(filling, "") ? Filling.valueOf(filling) : null;
        Facing facingEnum = !Objects.equals(facing, null) && !Objects.equals(facing, "") ? Facing.valueOf(facing) : null;

        return baseboardRepository.findAllWithFilters(fabricEnum, fillingEnum, facingEnum, sort);
    }

    public Baseboard getBaseboardById(UUID id) {
        return baseboardRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return baseboardRepository
                .findByName(name)
                .orElse(new Baseboard())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return baseboardRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.BASEBOARD;
    }
}
