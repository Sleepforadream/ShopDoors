package com.shopdoors.dao.entity.product.furniture;

import com.shopdoors.dao.entity.abstracted.Furniture;
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
@Table(name = "peephole")
public class Peephole extends Furniture {

}
