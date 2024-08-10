package com.shopdoors.controller.product;

import com.shopdoors.dao.entity.product.abstracted.Product;
import com.shopdoors.dao.entity.product.Cart;
import com.shopdoors.dao.entity.product.CartItem;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.enums.product.ProductType;
import com.shopdoors.dao.repository.product.CartRepository;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.product.ProductService;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private final CartRepository cartRepository;
    private final AuthorizeUserDetailsService userService;
    private final ImageService imageService;
    private final Map<ProductType, ProductService> productServices;

    @Autowired
    public CartController(
            CartRepository cartRepository,
            AuthorizeUserDetailsService userService,
            ImageService imageService,
            List<ProductService> productServices
    ) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.productServices = productServices.stream()
                .collect(Collectors.toMap(ProductService::getProductType, Function.identity()));
    }

    private ProductService getProductServiceByType(String type) {
        return productServices.get(ProductType.valueOf(type));
    }

    @GetMapping("/cart")
    public String viewCart(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        User user = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findByUser(user).orElseGet(Cart::new);

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            String productImageName = product.getImagePath();
            product.setImagePath(imageService.getImgUrl(productImageName));
        }

        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("cart", cart);
        return "products/cart";
    }

    @PostMapping("/cart/add/{id}/{type}")
    public String addToCart(@PathVariable UUID id, @PathVariable String type, @RequestParam int quantity, Principal principal) {
        if (principal == null) return "redirect:/login";

        User user = userService.findByUsername(principal.getName());

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        ProductService productService = getProductServiceByType(type);
        Product product = productService.getProductById(id);
        cart.addItemOrUpdate(product, ProductType.valueOf(type), quantity);
        cartRepository.save(cart);

        return "redirect:/cart";
    }

    @PostMapping("/cart/update/{id}")
    public String updateCartItemQuantity(@PathVariable UUID id, @RequestParam int quantity, Principal principal) {
        if (principal == null) return "redirect:/login";

        User user = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findByUser(user).orElse(null);

        if (cart != null) {
            CartItem item = cart.getItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getUuid().equals(id))
                    .findFirst()
                    .orElse(null);

            if (item != null) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    cart.removeItem(item);
                }
                cartRepository.save(cart);
            }
        }

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable UUID id, Principal principal) {
        if (principal == null) return "redirect:/login";

        User user = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findByUser(user).orElse(null);

        if (cart == null) {
            return "redirect:/cart?error=Корзина не найдена";
        }

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getUuid().equals(id))
                .findFirst()
                .orElse(null);

        if (itemToRemove == null) {
            return "redirect:/cart?error=Товар не найден в корзине";
        }

        cart.removeItem(itemToRemove);
        cartRepository.save(cart);

        return "redirect:/cart";
    }
}