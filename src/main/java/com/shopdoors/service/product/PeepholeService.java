package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.furniture.Peephole;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.PeepholeType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.PeepholeRepository;
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
public class PeepholeService {
    private final PeepholeRepository peepholeRepository;

    public List<Peephole> getFilteredPeepholes(
            String sortBy,
            String order,
            String fabric,
            String metal,
            String coating,
            Integer minimumDepth,
            Integer maximumDepth,
            String peepholeType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;
        PeepholeType peepholeTypeEnum = !Objects.equals(peepholeType, null) && !Objects.equals(peepholeType, "") ? PeepholeType.valueOf(peepholeType) : null;

        return peepholeRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, minimumDepth, maximumDepth, peepholeTypeEnum, sort);
    }

    public Peephole getPeepholeById(Long id) {
        return peepholeRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return peepholeRepository
                .findByName(name)
                .orElse(new Peephole())
                .getImagePath();
    }
}
