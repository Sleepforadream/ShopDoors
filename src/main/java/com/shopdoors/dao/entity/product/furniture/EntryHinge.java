package com.shopdoors.dao.entity.product.furniture;

import com.shopdoors.dao.entity.abstracted.Hinge;
import com.shopdoors.dao.enums.product.ProductType;
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
@Table(name = "entry_hinge")
public class EntryHinge extends Hinge {

    @Override
    public ProductType getType() {
        return ProductType.ROOM_HINGE;
    }
}
