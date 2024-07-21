package com.shopdoors.dao.entity.product.furniture;

import com.shopdoors.dao.entity.abstracted.Furniture;
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
@Table(name = "handle")
public class Handle extends Furniture {

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Socket socket;

    @Column(length = 100, nullable = false)
    private int rodLength;
}
