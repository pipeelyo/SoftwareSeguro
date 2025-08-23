package com.sgis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @NotBlank(message = "Product ID cannot be null or empty.")
    @Size(max = 50, message = "Product ID cannot be longer than 50 characters.")
    private String id;

    @NotBlank(message = "Product name cannot be null or empty.")
    @Column(nullable = false)
    private String name;

    @Positive(message = "Product price must be greater than zero.")
    @Column(nullable = false)
    private double price;

    @Min(value = 0, message = "Product stock cannot be negative.")
    @Column(nullable = false)
    private int stock;

}
