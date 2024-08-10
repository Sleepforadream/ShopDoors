package com.shopdoors.dao.entity.product.abstracted;

import com.shopdoors.dao.enums.product.Filling;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.enums.product.Side;
import com.shopdoors.dao.enums.user.Fabric;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 100)
    private String imagePath;

    @Column(length = 100, nullable = false)
    private double price;

    @Column(length = 100, nullable = false)
    private int height;

    @Column(length = 100, nullable = false)
    private int width;

    @Column(length = 100, nullable = false)
    private int depth;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Side side;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Filling filling;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Fabric fabric;

    public abstract ProductType getType();
}
