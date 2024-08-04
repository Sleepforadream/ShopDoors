package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.molding.Baseboard;
import com.shopdoors.dao.entity.product.molding.Jamb;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.MoldingType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.BaseboardRepository;
import com.shopdoors.dao.repository.product.JambRepository;
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
public class JambService {
    private final JambRepository jambRepository;

    public List<Jamb> getFilteredJamb(
            String sortBy,
            String order,
            String fabric,
            String filling,
            String facing,
            String moldingType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Filling fillingEnum = !Objects.equals(filling, null) && !Objects.equals(filling, "") ? Filling.valueOf(filling) : null;
        Facing facingEnum = !Objects.equals(facing, null) && !Objects.equals(facing, "") ? Facing.valueOf(facing) : null;
        MoldingType moldingTypeEnum = !Objects.equals(moldingType, null) && !Objects.equals(moldingType, "") ? MoldingType.valueOf(moldingType) : null;

        return jambRepository.findAllWithFilters(fabricEnum, fillingEnum, facingEnum, moldingTypeEnum, sort);
    }

    public Jamb getJambById(Long id) {
        return jambRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return jambRepository
                .findByName(name)
                .orElse(new Jamb())
                .getImagePath();
    }
}
