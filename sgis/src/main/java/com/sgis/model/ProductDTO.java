package com.sgis.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
    @NotBlank(message = "Product ID cannot be null or empty.")
    @Size(max = 50, message = "Product ID cannot be longer than 50 characters.")
    String id,

    @NotBlank(message = "Product name cannot be null or empty.")
    String name,

    @Positive(message = "Product price must be greater than zero.")
    double price,

    @Min(value = 0, message = "Product stock cannot be negative.")
    int stock
) {}
