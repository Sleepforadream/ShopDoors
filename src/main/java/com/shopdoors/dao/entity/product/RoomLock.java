package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Lock;
import com.shopdoors.dao.enums.product.LockType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "room_lock")
public class RoomLock extends Lock {

    @Column(length = 100, nullable = false)
    private boolean isMagnetic;

    @Column(length = 100, nullable = false)
    private LockType lockType;
}
