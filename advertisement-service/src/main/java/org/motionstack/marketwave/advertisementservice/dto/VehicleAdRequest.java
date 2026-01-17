package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Request DTO for creating/updating a vehicle advertisement")
public class VehicleAdRequest {

    @NotNull(message = "Owner ID is required")
    @Schema(description = "UUID of the ad owner", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID ownerId;

    @Schema(description = "Store ID if posted by a dealership", example = "1")
    private Long storeId;

    @NotBlank(message = "Title is required")
    @Size(max = 500, message = "Title cannot exceed 500 characters")
    @Schema(description = "Advertisement title", example = "2020 Toyota Corolla XLE - Low Mileage")
    private String title;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    @Schema(description = "Detailed description of the vehicle", 
            example = "Well-maintained sedan with excellent fuel economy. Single owner, full service history available.")
    private String description;

    @NotNull(message = "Brand ID is required")
    @Schema(description = "Vehicle brand ID", example = "1")
    private Long brandId;

    @NotNull(message = "Model ID is required")
    @Schema(description = "Vehicle model ID", example = "5")
    private Long modelId;

    @Schema(description = "Body type ID", example = "2")
    private Long bodyTypeId;

    @Schema(description = "Category ID", example = "1")
    private Long categoryId;

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be 1900 or later")
    @Max(value = 2100, message = "Year must be 2100 or earlier")
    @Schema(description = "Manufacturing year", example = "2020")
    private Integer year;

    @NotNull(message = "Mileage is required")
    @Min(value = 0, message = "Mileage cannot be negative")
    @Schema(description = "Vehicle mileage in kilometers", example = "45000")
    private Integer mileage;

    @NotBlank(message = "Fuel type is required")
    @Schema(description = "Fuel type", example = "Gasoline", allowableValues = {"Gasoline", "Diesel", "Electric", "Hybrid", "Plugin Hybrid"})
    private String fuelType;

    @NotBlank(message = "Transmission is required")
    @Schema(description = "Transmission type", example = "Automatic", allowableValues = {"Manual", "Automatic", "CVT", "Semi-Automatic"})
    private String transmission;

    @NotBlank(message = "Condition is required")
    @Schema(description = "Vehicle condition", example = "Excellent", allowableValues = {"New", "Excellent", "Good", "Fair", "Poor"})
    private String condition;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Schema(description = "Vehicle price", example = "25000.00")
    private BigDecimal price;

    @NotBlank(message = "Currency is required")
    @Size(min = 3, max = 3, message = "Currency must be 3 characters (ISO 4217)")
    @Schema(description = "Currency code (ISO 4217)", example = "USD")
    private String currency;

    @NotBlank(message = "Country is required")
    @Schema(description = "Country where vehicle is located", example = "United States")
    private String country;

    @NotBlank(message = "City is required")
    @Schema(description = "City where vehicle is located", example = "Los Angeles")
    private String city;

    @Schema(description = "Latitude coordinate", example = "34.0522")
    private BigDecimal latitude;

    @Schema(description = "Longitude coordinate", example = "-118.2437")
    private BigDecimal longitude;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    @Schema(description = "Full address", example = "123 Main Street, Los Angeles, CA 90001")
    private String address;

    @Schema(description = "Advertisement status", example = "ACTIVE", allowableValues = {"DRAFT", "ACTIVE", "INACTIVE", "SOLD", "EXPIRED"})
    private String status;

    @Schema(description = "Whether bidding is enabled", example = "false")
    private Boolean isBiddingEnabled;

    @Schema(description = "Whether pre-order is enabled", example = "false")
    private Boolean isPreorderEnabled;
}
