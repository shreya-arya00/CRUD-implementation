package com.example.myapp.service;

import com.example.myapp.dto.ProductDTO;
import com.example.myapp.entity.Product;
import com.example.myapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productDTO.toEntity();
        product = productRepository.save(product);
        return ProductDTO.fromEntity(product);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::fromEntity);
    }

    public List<ProductDTO> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword)
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
