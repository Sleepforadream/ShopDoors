package com.shopdoors.dao.entity.product.furniture;

import com.shopdoors.dao.entity.abstracted.Furniture;
import com.shopdoors.dao.enums.product.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "fastening")
public class Fastening extends Furniture {

    @Override
    public ProductType getType() {
        return ProductType.FASTENING;
    }
}
