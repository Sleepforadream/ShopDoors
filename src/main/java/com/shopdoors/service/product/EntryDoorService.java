package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.door.EntryDoor;
import com.shopdoors.dao.enums.product.Color;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.EntryDoorRepository;
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
public class EntryDoorService {
    private final EntryDoorRepository entryDoorRepository;

    public List<EntryDoor> getFilteredEntryDoors(
            String sortBy,
            String order,
            String fabric,
            String metal,
            String color,
            String filling
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Color colorEnum = !Objects.equals(color, null) && !Objects.equals(color, "") ? Color.valueOf(color) : null;
        Filling fillingEnum = !Objects.equals(filling, null) && !Objects.equals(filling, "") ? Filling.valueOf(filling) : null;

        return entryDoorRepository.findAllWithFilters(fabricEnum, metalEnum, colorEnum, fillingEnum, sort);
    }

    public EntryDoor getEntryDoorById(Long id) {
        return entryDoorRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return entryDoorRepository
                .findByName(name)
                .orElse(new EntryDoor())
                .getImagePath();
    }
}
