package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.furniture.Fastening;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.FasteningRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class FasteningService {
    private final FasteningRepository fasteningRepository;

    public List<Fastening> getFilteredFastenings(
            String sortBy,
            String order,
            String fabric,
            String metal,
            String coating
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;

        return fasteningRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, sort);
    }

    public Fastening getFasteningById(Long id) {
        return fasteningRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return fasteningRepository
                .findByName(name)
                .orElse(new Fastening())
                .getImagePath();
    }
}
