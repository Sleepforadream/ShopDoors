package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Door;
import com.shopdoors.dao.enums.product.Facing;
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
@Table(name = "room_door")
public class RoomDoor extends Door {

    @Column(length = 100, nullable = false)
    private Facing facing;

    @OneToOne
    private Handle handle;

    @OneToOne
    private RoomHinge roomHinge;

    @OneToOne
    private Retainer retainer;

    @OneToOne
    private RoomLock roomLock;

    @OneToOne
    private Jamb jamb;

    @OneToOne
    private Pannier pannier;

    @OneToOne
    private AdditionalElement additionalElement;
}
