package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.product.molding.Panel;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.PanelType;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.PanelRepository;
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
public class PanelService implements ProductService {
    private final PanelRepository panelRepository;

    public List<Panel> getFilteredPanel(
            String sortBy,
            String order,
            String fabric,
            String filling,
            String facing,
            String panelType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Filling fillingEnum = !Objects.equals(filling, null) && !Objects.equals(filling, "") ? Filling.valueOf(filling) : null;
        Facing facingEnum = !Objects.equals(facing, null) && !Objects.equals(facing, "") ? Facing.valueOf(facing) : null;
        PanelType panelTypeEnum = !Objects.equals(panelType, null) && !Objects.equals(panelType, "") ? PanelType.valueOf(panelType) : null;

        return panelRepository.findAllWithFilters(fabricEnum, fillingEnum, facingEnum, panelTypeEnum, sort);
    }

    public Panel getPanelById(UUID id) {
        return panelRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return panelRepository
                .findByName(name)
                .orElse(new Panel())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return panelRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.PANEL;
    }
}
