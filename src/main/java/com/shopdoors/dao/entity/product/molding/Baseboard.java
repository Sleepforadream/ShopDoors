package com.shopdoors.dao.entity.product.molding;

import com.shopdoors.dao.entity.abstracted.Moldings;
import com.shopdoors.dao.entity.product.furniture.Bracing;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@Table(name = "baseboard")
public class Baseboard extends Moldings {

    @OneToOne
    private Bracing bracing;
}