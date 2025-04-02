package com.example.storeapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    // Metod för att visa beställningsformuläret
    @GetMapping("/order")
    public String showOrderPage(@RequestParam("product") String product,
                                @RequestParam("price") String price,
                                Model model) {
        // Lägg till produktinformationen till modellen
        model.addAttribute("product", product);
        model.addAttribute("price", price);

        // Returnera "order", vilket kommer att matcha order.html
        return "order";
    }



    @PostMapping("/place-order")
    public String placeOrder(@RequestParam Map<String, String> orderData, Model model) {
        String product = orderData.get("product");
        String price = orderData.get("price");
        String name = orderData.get("name");
        String phone = orderData.get("phone");
        String email = orderData.get("email");
        String address = orderData.get("address");
        String postalCode = orderData.get("postalCode");
        String city = orderData.get("city");

        // Lägg till informationen till modellen för användning i confirmation.html
        model.addAttribute("product", product);
        model.addAttribute("price", price);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("postalCode", postalCode);
        model.addAttribute("city", city);

        // Om du vill logga ut information för felsökning:
        System.out.println("Produkt: " + product);
        System.out.println("Pris: " + price);

        // Skicka användardata till beställningsbekräftelsen
        return "confirmation"; // Visar confirmation.html
    }

}
