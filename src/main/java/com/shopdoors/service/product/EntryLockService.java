package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.product.furniture.EntryLock;
import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.DefenseClass;
import com.shopdoors.dao.enums.product.KeyType;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.repository.product.EntryLockRepository;
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
public class EntryLockService implements ProductService {
    private final EntryLockRepository entryLockRepository;

    public List<EntryLock> getFilteredEntryLocks(
            String sortBy,
            String order,
            String fabric,
            String metal,
            String coating,
            String defenseClass,
            String firstKeyType,
            String secondKeyType
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

        Fabric fabricEnum = !Objects.equals(fabric, null) && !Objects.equals(fabric, "") ? Fabric.valueOf(fabric) : null;
        Metal metalEnum = !Objects.equals(metal, null) && !Objects.equals(metal, "") ? Metal.valueOf(metal) : null;
        Coating coatingEnum = !Objects.equals(coating, null) && !Objects.equals(coating, "") ? Coating.valueOf(coating) : null;
        DefenseClass defenseClassEnum = !Objects.equals(defenseClass, null) && !Objects.equals(defenseClass, "") ? DefenseClass.valueOf(defenseClass) : null;
        KeyType firstKeyTypeEnum = !Objects.equals(firstKeyType, null) && !Objects.equals(firstKeyType, "") ? KeyType.valueOf(firstKeyType) : null;
        KeyType secondKeyTypeEnum = !Objects.equals(secondKeyType, null) && !Objects.equals(secondKeyType, "") ? KeyType.valueOf(secondKeyType) : null;

        return entryLockRepository.findAllWithFilters(fabricEnum, metalEnum, coatingEnum, defenseClassEnum, firstKeyTypeEnum, secondKeyTypeEnum, sort);
    }

    public EntryLock getEntryLockById(UUID id) {
        return entryLockRepository.findById(id).orElseThrow();
    }

    public String getImgPathByName(String name) {
        log.info("Get img path for product - {}", name);
        return entryLockRepository
                .findByName(name)
                .orElse(new EntryLock())
                .getImagePath();
    }

    @Override
    public Product getProductById(UUID id) {
        return entryLockRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ENTRY_LOCK;
    }
}
