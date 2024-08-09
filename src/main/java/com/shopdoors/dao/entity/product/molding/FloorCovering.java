package com.shopdoors.dao.entity.product.molding;

import com.shopdoors.dao.entity.abstracted.Moldings;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.WaterResistanceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "floor_covering")
public class FloorCovering extends Moldings {

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    WaterResistanceType waterResistanceType;

    @Override
    public ProductType getType() {
        return ProductType.FLOOR_COVERING;
    }
}
