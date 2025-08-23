package com.sgis.service;

import com.sgis.model.ProductDTO;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * Adds a new product to the system.
     * @param productDTO The data transfer object containing the product details.
     * @return The created product as a DTO.
     * @throws IllegalArgumentException if a product with the same ID already exists.
     */
    ProductDTO addProduct(ProductDTO productDTO);

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product to find.
     * @return An Optional containing the found product as a DTO, or empty if not found.
     */
    Optional<ProductDTO> findProductById(String id);

    /**
     * Retrieves all products.
     * @return A list of all product DTOs.
     */
    List<ProductDTO> findAllProducts();

    /**
     * Updates an existing product.
     * @param id The ID of the product to update.
     * @param productDTO The DTO with the updated information.
     * @return The updated product as a DTO.
     * @throws IllegalArgumentException if the product is not found.
     */
    ProductDTO updateProduct(String id, ProductDTO productDTO);

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete.
     */
    void deleteProduct(String id);
}
