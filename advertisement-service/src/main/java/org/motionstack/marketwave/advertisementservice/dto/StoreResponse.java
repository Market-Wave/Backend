package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Response DTO for store")
public class StoreResponse {

    @Schema(description = "Store ID", example = "1")
    private Long id;

    @Schema(description = "Owner UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID ownerId;

    @Schema(description = "Store name", example = "Premium Auto Dealers")
    private String name;

    @Schema(description = "Unique slug", example = "premium-auto-dealers")
    private String slug;

    @Schema(description = "Store description", example = "Leading automotive dealership.")
    private String description;

    @Schema(description = "Country", example = "United States")
    private String country;

    @Schema(description = "City", example = "New York")
    private String city;

    @Schema(description = "Latitude", example = "40.7128")
    private BigDecimal latitude;

    @Schema(description = "Longitude", example = "-74.0060")
    private BigDecimal longitude;

    @Schema(description = "Full address", example = "456 Broadway, New York, NY 10013")
    private String address;

    @Schema(description = "Store status", example = "ACTIVE")
    private String status;
}
