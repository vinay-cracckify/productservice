package com.cracckify.productservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private UUID id;

    @NotBlank(message = "Title is required")
    private String title;

    @Size(max = 2048)
    private String description;

    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    @Min(value = 0, message = "Price must be positive or zero")
    private double price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQuantity;

    @JsonProperty("available")
    private boolean available;

}
