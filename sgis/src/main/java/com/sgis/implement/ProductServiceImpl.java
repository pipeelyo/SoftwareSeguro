package com.sgis.implement;

import com.sgis.entity.Product;
import com.sgis.model.ProductDTO;
import com.sgis.repository.ProductRepository;
import com.sgis.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(value = "products", key = "#result.id")
    public ProductDTO addProduct(ProductDTO productDTO) {
        if (productRepository.findById(productDTO.id()).isPresent()) {
            throw new IllegalArgumentException("A product with ID " + productDTO.id() + " already exists.");
        }
        Product product = toEntity(productDTO);
        productRepository.save(product);
        return productDTO;
    }

    @Override
    @Cacheable(value = "products", key = "#id", unless = "#result == null")
    public Optional<ProductDTO> findProductById(String id) {
        return productRepository.findById(id).map(this::toDTO);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "products", key = "#id")
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found."));

        existingProduct.setName(productDTO.name());
        existingProduct.setPrice(productDTO.price());
        existingProduct.setStock(productDTO.stock());

        productRepository.save(existingProduct);
        return toDTO(existingProduct);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(String id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    private Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.stock());
    }
}
