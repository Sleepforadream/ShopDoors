package com.shopdoors.dao.entity.abstracted;

import com.shopdoors.dao.enums.product.Coating;
import com.shopdoors.dao.enums.product.Metal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Furniture extends Product {

    @Column(length = 100, nullable = false)
    private Coating coating;

    @Column(length = 100, nullable = false)
    private Metal metal;
}
