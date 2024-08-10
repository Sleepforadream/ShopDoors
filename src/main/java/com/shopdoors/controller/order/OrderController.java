package com.shopdoors.controller.order;

import com.shopdoors.dao.entity.order.CustomerOrder;
import com.shopdoors.dao.entity.product.Cart;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.repository.order.CustomerOrderRepository;
import com.shopdoors.dao.repository.product.CartRepository;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.OrderService;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AuthorizeUserDetailsService userService;
    private final CartRepository cartRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final TransactionRunner transactionRunner;
    private final ImageService imageService;

    @PostMapping("/checkout")
    public String showCheckoutPage(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        User user = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findByUser(user).orElseGet(Cart::new);

        if (cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "order/checkout";
    }

    @PostMapping("/order-confirmation")
    public String completeCheckout(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        User user = userService.findByUsername(principal.getName());
        CustomerOrder order = orderService.createOrder(user);
        model.addAttribute("message", "Заказ успешно оформлен!");
        model.addAttribute("order", order);
        return "order/order_confirmation";
    }

    @GetMapping("/orders")
    public String viewOrderHistory(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());

        User currentUser = userService.findByUsername(principal.getName());
        List<CustomerOrder> orders = orderService.getOrderHistory(currentUser);
        model.addAttribute("orders", orders);
        return "/order/orders";
    }

    @GetMapping("/orders/{id}")
    public String viewCurrentOrder(Principal principal, Model model, @PathVariable long id) {
        if (principal == null) return "redirect:/login";

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.equals("anonymousUser")) {
            String userImageName = userService.getImgPathByEmail(username);
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
        }

        CustomerOrder currentOrder = orderService.getOrderById(id);
        model.addAttribute("currentOrder", currentOrder);
        return "/order/order_detail";
    }

    @DeleteMapping("/orders/{id}")
    public String deleteCurrentOrder(Principal principal, Model model, @PathVariable long id) {
        if (principal == null) return "redirect:/login";
        CustomerOrder currentOrder = orderService.getOrderById(id);
        model.addAttribute("currentOrder", currentOrder);
        transactionRunner.doInTransaction(() -> {
            customerOrderRepository.delete(currentOrder);
        });
        return "redirect:/orders";
    }
}