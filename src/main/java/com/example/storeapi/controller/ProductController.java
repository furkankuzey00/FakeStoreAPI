package com.example.storeapi.controller;

import com.example.storeapi.service.ProductService;
import com.example.storeapi.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList";  // Laddar produktlistan
    }

    @GetMapping("/")
    public String home() {
        return "home";  // Hem-sidan
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";  // Kontakt-sidan
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";  // Om oss-sidan
    }

    // Den här metoden används för att visa beställningsformuläret
    @GetMapping("/order")
    public String getOrderPage() {
        return "order";  // Beställningssidan
    }
}
