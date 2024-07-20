package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Lock;
import com.shopdoors.dao.enums.product.DefenseClass;
import com.shopdoors.dao.enums.product.KeyType;
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
@Table(name = "entry_lock")
public class EntryLock extends Lock {

    @Column(length = 100, nullable = false)
    private DefenseClass defenseClass;

    @Column(length = 100, nullable = false)
    private KeyType firstKeyType;

    @Column(length = 100)
    private KeyType secondKeyType;
}
