package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Request DTO for creating/updating a store")
public class StoreRequest {

    @NotNull(message = "Owner ID is required")
    @Schema(description = "UUID of the store owner", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID ownerId;

    @NotBlank(message = "Store name is required")
    @Schema(description = "Store name", example = "Premium Auto Dealers")
    private String name;

    @NotBlank(message = "Store slug is required")
    @Schema(description = "Unique URL slug", example = "premium-auto-dealers")
    private String slug;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Schema(description = "Store description", example = "Leading automotive dealership with 20+ years of experience.")
    private String description;

    @NotBlank(message = "Country is required")
    @Schema(description = "Country", example = "United States")
    private String country;

    @NotBlank(message = "City is required")
    @Schema(description = "City", example = "New York")
    private String city;

    @Schema(description = "Latitude coordinate", example = "40.7128")
    private BigDecimal latitude;

    @Schema(description = "Longitude coordinate", example = "-74.0060")
    private BigDecimal longitude;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    @Schema(description = "Full address", example = "456 Broadway, New York, NY 10013")
    private String address;

    @Schema(description = "Store status", example = "ACTIVE", allowableValues = {"PENDING", "ACTIVE", "SUSPENDED", "CLOSED"})
    private String status;
}
