package com.shopdoors.dao.entity.product.door;

import com.shopdoors.dao.entity.abstracted.Door;
import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.entity.product.furniture.Retainer;
import com.shopdoors.dao.entity.product.furniture.RoomHinge;
import com.shopdoors.dao.entity.product.furniture.RoomLock;
import com.shopdoors.dao.entity.product.molding.AdditionalElement;
import com.shopdoors.dao.entity.product.molding.Jamb;
import com.shopdoors.dao.entity.product.molding.Pannier;
import com.shopdoors.dao.enums.product.Facing;
import com.shopdoors.dao.enums.product.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
    @Enumerated(EnumType.STRING)
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

    @Override
    public ProductType getType() {
        return ProductType.ROOM_DOOR;
    }
}
