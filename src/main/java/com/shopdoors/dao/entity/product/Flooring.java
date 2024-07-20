package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Moldings;
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
@Table(name = "flooring")
public class Flooring extends Moldings {

    @Column(length = 100, nullable = false)
    boolean isWaterResistance;
}
