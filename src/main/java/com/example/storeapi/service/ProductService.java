package com.example.storeapi.service;

import com.example.storeapi.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";
        try {
            Product[] products = restTemplate.getForObject(url, Product[].class);
            return products != null ? Arrays.asList(products) : Collections.emptyList();
        } catch (RestClientException e) {
            System.err.println("Kunde inte hämta produkter: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
