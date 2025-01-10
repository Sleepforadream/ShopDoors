package com.shopdoors.controller.order;

import com.shopdoors.dao.entity.order.FavoriteItem;
import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.service.FavoriteService;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final AuthorizeUserDetailsService userService;
    private final ImageService imageService;

    @GetMapping("/favorites")
    public String viewFavorites(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        List<FavoriteItem> favoriteItems = favoriteService.getUserFavorites(user).getItems();

        for (FavoriteItem item : favoriteItems) {
            Product product = item.getProduct();
            String productImageName = product.getImagePath();
            product.setImagePath(imageService.getImgUrl(productImageName));
        }

        model.addAttribute("favorites", favoriteItems);
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        return "order/favorites";
    }

    @PostMapping("/favorites/toggle/{id}/{type}")
    public String toggleFavorite(@PathVariable UUID id, @PathVariable String type, Principal principal, HttpServletRequest request) {
        User user = userService.findByUsername(principal.getName());
        favoriteService.toggleProductFavoriteStatus(user, id, type);

        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/favorites/remove/{id}/{type}")
    public String removeFromFavorites(@PathVariable UUID id, @PathVariable String type, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        favoriteService.removeProductFromFavorites(user, id, type);

        return "redirect:order/favorites";
    }
}