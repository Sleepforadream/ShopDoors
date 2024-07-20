package com.shopdoors.dao.entity.abstracted;

import com.shopdoors.dao.enums.product.Facing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
public abstract class Moldings extends Product {

    @Column(length = 100, nullable = false)
    private Facing facing;
}
