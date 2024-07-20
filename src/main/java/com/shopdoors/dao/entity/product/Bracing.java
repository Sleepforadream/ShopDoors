package com.shopdoors.dao.entity.product;

import com.shopdoors.dao.entity.abstracted.Furniture;
import com.shopdoors.dao.enums.product.Socket;
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
@NoArgsConstructor
@Table(name = "bracing")
public class Bracing extends Furniture {

}
