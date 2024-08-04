package com.shopdoors.dao.entity.abstracted;

import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Furniture extends Product {

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Coating coating;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Metal metal;
}
