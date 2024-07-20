package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Door;
import com.shopdoors.dao.enums.product.Color;
import com.shopdoors.dao.enums.product.Metal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Color color;

    @Column(length = 100, nullable = false)
    private Metal metal;
}
