package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.product.door.RoomDoor;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.RoomDoorRepository;
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
public class RoomDoorService implements ProductService {
    private final RoomDoorRepository roomDoorRepository;

    public List<RoomDoor> getFilteredRoomDoors(String sortBy, String order, String fabric, String facing, String filling) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Facing facingEnum = !Objects.equals(facing, null) && !Objects.equals(facing, "") ? Facing.valueOf(facing) : null;
        Filling fillingEnum = !Objects.equals(filling, null) && !Objects.equals(filling, "") ? Filling.valueOf(filling) : null;

        return roomDoorRepository.findAllWithFilters(fabricEnum, facingEnum, fillingEnum, sort);
    }

    public RoomDoor getRoomDoorById(UUID id) {
        return roomDoorRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return roomDoorRepository
                .findByName(name)
                .orElse(new RoomDoor())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return roomDoorRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ROOM_DOOR;
    }
}
