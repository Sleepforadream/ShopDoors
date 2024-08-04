package com.shopdoors.dao.entity.abstracted;

import com.shopdoors.dao.enums.product.Facing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
public abstract class Moldings extends Product {

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Facing facing;
}
