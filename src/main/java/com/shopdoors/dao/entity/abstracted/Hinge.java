package com.shopdoors.dao.entity.abstracted;

import com.shopdoors.dao.enums.product.HingeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Hinge extends Furniture {
    @Column(length = 100, nullable = false)
    private int count;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private HingeType hingeType;
}
