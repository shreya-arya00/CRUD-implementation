package com.example.myapp.controller;

import com.example.myapp.dto.ProductDTO;
import com.example.myapp.entity.Product;
import com.example.myapp.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long productId1;
    private Long productId2;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();

        Product product1 = new Product("Test Product 1", "Description 1", 100.0);
        Product product2 = new Product("Test Product 2", "Description 2", 150.0);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);

        productId1 = product1.getId();
        productId2 = product2.getId();

        // Print debug information
        System.out.println("Product 1: " + product1);
        System.out.println("Product 2: " + product2);
    }

    @Test
    public void createProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO(null, "Test Product", "Description", 100.0);
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    public void getProductById() throws Exception {
        mockMvc.perform(get("/api/products/" + productId1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product 1"));
    }

    @Test
    public void searchProducts() throws Exception {
        mockMvc.perform(get("/api/products/search")
                        .param("keyword", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product 1"));
    }
}
