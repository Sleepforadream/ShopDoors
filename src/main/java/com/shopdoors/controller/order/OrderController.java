package com.shopdoors.controller.order;

import com.shopdoors.dao.entity.order.CustomerOrder;
import com.shopdoors.dao.entity.product.Cart;
import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.repository.order.CustomerOrderRepository;
import com.shopdoors.dao.repository.product.CartRepository;
import com.shopdoors.service.OrderService;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
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
    private final AuthorizeUserDetailsService userDetailsService;
    private final TransactionRunner transactionRunner;

    @PostMapping("/checkout")
    public String showCheckoutPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findByUser(user).orElseGet(Cart::new);

        if (cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        return "order/checkout";
    }

    @PostMapping("/order-confirmation")
    public String completeCheckout(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        CustomerOrder order = orderService.createOrder(user);
        model.addAttribute("message", "Заказ успешно оформлен!");
        model.addAttribute("order", order);
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        return "order/order_confirmation";
    }

    @GetMapping("/orders")
    public String viewOrderHistory(Principal principal, Model model) {
        User currentUser = userService.findByUsername(principal.getName());
        List<CustomerOrder> orders = orderService.getOrderHistory(currentUser);
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("orders", orders);
        return "/order/orders";
    }

    @GetMapping("/orders/{id}")
    public String viewCurrentOrder(Model model, @PathVariable long id) {
        CustomerOrder currentOrder = orderService.getOrderById(id);
        model.addAttribute("imgProfileUrl", userDetailsService.getCurrentUserImgPath());
        model.addAttribute("currentOrder", currentOrder);
        return "/order/order_detail";
    }

    @DeleteMapping("/orders/{id}")
    public String deleteCurrentOrder(Model model, @PathVariable long id) {
        CustomerOrder currentOrder = orderService.getOrderById(id);
        model.addAttribute("currentOrder", currentOrder);
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        transactionRunner.doInTransaction(() -> customerOrderRepository.delete(currentOrder));
        return "redirect:/orders";
    }
}