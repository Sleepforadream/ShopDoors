package com.shopdoors.dao.entity.product.molding;

import com.shopdoors.dao.entity.abstracted.Moldings;
import com.shopdoors.dao.enums.product.MoldingType;
import com.shopdoors.dao.enums.product.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "jamb")
public class Jamb extends Moldings {

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    MoldingType moldingType;

    @Override
    public ProductType getType() {
        return ProductType.JAMB;
    }
}
