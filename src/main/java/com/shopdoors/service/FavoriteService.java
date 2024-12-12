package com.shopdoors.service;

import com.shopdoors.dao.entity.order.Favorite;
import com.shopdoors.dao.entity.order.FavoriteItem;
import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.repository.order.FavoriteRepository;
import com.shopdoors.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final Map<ProductType, ProductService> productServices;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, List<ProductService> productServices) {
        this.favoriteRepository = favoriteRepository;
        this.productServices = productServices.stream()
                .collect(Collectors.toMap(ProductService::getProductType, Function.identity()));
    }

    private ProductService getProductServiceByType(String type) {
        return productServices.get(ProductType.valueOf(type));
    }

    public void toggleProductFavoriteStatus(User user, UUID productId, String type) {
        Favorite favorite = favoriteRepository.findByUser(user).orElseGet(() -> {
            Favorite newFavorite = new Favorite();
            newFavorite.setUser(user);
            return favoriteRepository.save(newFavorite);
        });

        // Ищем продукт в избранном
        Optional<FavoriteItem> existingItem = favorite.getItems().stream()
                .filter(item -> item.getProduct().getUuid().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Если продукт уже в избранном, удаляем его
            favorite.getItems().remove(existingItem.get());
        } else {
            // Если продукт не в избранном, добавляем его
            ProductService productService = getProductServiceByType(type);
            Product product = productService.getProductById(productId);

            FavoriteItem favoriteItem = new FavoriteItem();
            favoriteItem.setFavorite(favorite);
            favoriteItem.setProduct(product);
            favorite.getItems().add(favoriteItem);
        }

        favoriteRepository.save(favorite);
    }

    public void removeProductFromFavorites(User user, UUID productId, String type) {
        Favorite favorite = favoriteRepository.findByUser(user).orElse(null);
        if (favorite != null) {
            favorite.getItems().removeIf(item -> item.getProduct().getUuid().equals(productId));
            favoriteRepository.save(favorite);
        }
    }

    public Favorite getUserFavorites(User user) {
        return favoriteRepository.findByUser(user).orElse(new Favorite());
    }
}