package com.shopdoors.dao.repository.product;

import com.shopdoors.dao.entity.product.molding.Panel;
import com.shopdoors.dao.entity.product.molding.Pannier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PannierRepository extends JpaRepository<Pannier, Long> {

}