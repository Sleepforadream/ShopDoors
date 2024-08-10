package com.shopdoors.dao.entity.product.door;

import com.shopdoors.dao.entity.product.abstracted.Door;
import com.shopdoors.dao.entity.product.furniture.EntryHinge;
import com.shopdoors.dao.entity.product.furniture.EntryLock;
import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.entity.product.furniture.Retainer;
import com.shopdoors.dao.entity.product.molding.Panel;
import com.shopdoors.dao.enums.product.Color;
import com.shopdoors.dao.enums.product.Metal;
import com.shopdoors.dao.enums.product.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entry_door")
public class EntryDoor extends Door {

    @OneToOne
    private Panel panelInside;

    @OneToOne
    private Panel panelOutside;

    @OneToOne
    private Handle handle;

    @OneToOne
    private EntryHinge entryHinge;

    @OneToOne
    private EntryLock firstEntryLock;

    @OneToOne
    private EntryLock secondEntryLock;

    @OneToOne
    private Retainer retainer;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Metal metal;

    @Override
    public ProductType getType() {
        return ProductType.ENTRY_DOOR;
    }
}
