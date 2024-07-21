package com.shopdoors.dao.entity.abstracted;

import com.shopdoors.dao.enums.product.Filling;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100)
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
}