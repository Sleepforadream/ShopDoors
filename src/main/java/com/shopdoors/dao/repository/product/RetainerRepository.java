package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.furniture.Peephole;
import com.shopdoors.dao.entity.product.furniture.Retainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetainerRepository extends JpaRepository<Retainer, Long> {

}