package com.shopdoors.dao.entity.product.furniture;

import com.shopdoors.dao.entity.abstracted.Furniture;
import com.shopdoors.dao.enums.product.PeepholeType;
import com.shopdoors.dao.enums.product.Socket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(length = 100, nullable = false)
    private int maximumDepth;

    @Column(length = 100, nullable = false)
    private int minimumDepth;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private PeepholeType peepholeType;
}
