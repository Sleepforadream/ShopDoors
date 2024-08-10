package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.product.furniture.Retainer;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.KeyRetainer;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.Socket;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.RetainerRepository;
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
public class RetainerService implements ProductService {
    private final RetainerRepository retainerRepository;

    public List<Retainer> getFilteredRetainers(
            String sortBy, String order, String fabric, String metal, String coating, String socket, String keyRetainer
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;
        Socket socketEnum = !Objects.equals(socket, null) && !Objects.equals(socket, "") ? Socket.valueOf(socket) : null;
        KeyRetainer keyRetainerEnum = !Objects.equals(keyRetainer, null) && !Objects.equals(keyRetainer, "") ? KeyRetainer.valueOf(keyRetainer) : null;

        return retainerRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, socketEnum, keyRetainerEnum, sort);
    }

    public Retainer getRetainerById(UUID id) {
        return retainerRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return retainerRepository
                .findByName(name)
                .orElse(new Retainer())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return retainerRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.RETAINER;
    }
}
