package com.shopdoors.dao.entity.product.abstracted;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
public abstract class Lock extends Furniture {

}
