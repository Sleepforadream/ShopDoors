package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.enums.product.ProductType;

import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID id);
    ProductType getProductType();
}