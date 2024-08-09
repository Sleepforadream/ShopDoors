package com.shopdoors.service.product;

import com.shopdoors.dao.entity.abstracted.Product;
import com.shopdoors.dao.entity.product.furniture.RoomLock;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.LockType;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.TongueType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.RoomLockRepository;
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
public class RoomLockService implements ProductService {
    private final RoomLockRepository roomLockRepository;

    public List<RoomLock> getFilteredRoomLocks(
            String sortBy, String order, String fabric, String metal, String coating, String tongueType, String lockType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;
        LockType lockTypeEnum = !Objects.equals(lockType, null) && !Objects.equals(lockType, "") ? LockType.valueOf(lockType) : null;
        TongueType tongueTypeEnum = !Objects.equals(tongueType, null) && !Objects.equals(tongueType, "") ? TongueType.valueOf(tongueType) : null;

        return roomLockRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, tongueTypeEnum, lockTypeEnum, sort);
    }

    public RoomLock getRoomLockById(UUID id) {
        return roomLockRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return roomLockRepository
                .findByName(name)
                .orElse(new RoomLock())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return roomLockRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ROOM_LOCK;
    }
}
