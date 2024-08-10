package com.shopdoors.dao.entity.product.molding;

import com.shopdoors.dao.entity.product.abstracted.Moldings;
import com.shopdoors.dao.entity.product.furniture.Fastening;
import com.shopdoors.dao.enums.product.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@Table(name = "baseboard")
public class Baseboard extends Moldings {

    @OneToOne
    private Fastening fastening;

    @Override
    public ProductType getType() {
        return ProductType.BASEBOARD;
    }
}
