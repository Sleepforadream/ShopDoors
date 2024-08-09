package com.shopdoors.service.product;

import com.shopdoors.dao.entity.abstracted.Product;
import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.Socket;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.HandleRepository;
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
public class HandleService implements ProductService {
    private final HandleRepository handleRepository;

    public List<Handle> getFilteredHandles(
            String sortBy, String order, String fabric, String metal, String coating, String socket, Integer rodLength
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;
        Socket socketEnum = !Objects.equals(socket, null) && !Objects.equals(socket, "") ? Socket.valueOf(socket) : null;

        return handleRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, socketEnum, rodLength, sort);
    }

    public Handle getHandleById(UUID id) {
        return handleRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return handleRepository
                .findByName(name)
                .orElse(new Handle())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return handleRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.HANDLE;
    }
}
