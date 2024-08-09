package com.shopdoors.dao.entity.product.furniture;

import com.shopdoors.dao.entity.abstracted.Furniture;
import com.shopdoors.dao.enums.product.KeyRetainer;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.Socket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "retainer")
public class Retainer extends Furniture {

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Socket socket;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private KeyRetainer keyRetainer;

    @Override
    public ProductType getType() {
        return ProductType.RETAINER;
    }
}
