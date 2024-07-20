package com.shopdoors.dao.entity.product.molding;

import com.shopdoors.dao.entity.abstracted.Moldings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "jamb")
public class Jamb extends Moldings {

    @Column(length = 100, nullable = false)
    boolean isTelescope;
}
