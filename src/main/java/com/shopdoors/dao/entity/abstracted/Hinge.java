package com.shopdoors.dao.entity.abstracted;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Hinge extends Furniture {
    @Column(length = 100, nullable = false)
    private int count;

    @Column(length = 100, nullable = false)
    private boolean isHide;
}
