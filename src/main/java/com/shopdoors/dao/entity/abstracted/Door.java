package com.shopdoors.dao.entity.abstracted;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
public abstract class Door extends Product {

}
