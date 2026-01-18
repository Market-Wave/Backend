package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Response DTO for vehicle advertisement")
public class VehicleAdResponse {

    @Schema(description = "Advertisement ID", example = "1")
    private Long id;

    @Schema(description = "UUID of the ad owner", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID ownerId;

    @Schema(description = "Store ID if posted by a dealership", example = "1")
    private Long storeId;

    @Schema(description = "Advertisement title", example = "2020 Toyota Corolla XLE - Low Mileage")
    private String title;

    @Schema(description = "Detailed description", example = "Well-maintained sedan with excellent fuel economy.")
    private String description;

    @Schema(description = "Vehicle brand ID", example = "1")
    private Long brandId;

    @Schema(description = "Vehicle model ID", example = "5")
    private Long modelId;

    @Schema(description = "Body type ID", example = "2")
    private Long bodyTypeId;

    @Schema(description = "Category ID", example = "1")
    private Long categoryId;

    @Schema(description = "Vehicle brand information")
    private VehicleBrandResponse brand;

    @Schema(description = "Vehicle model information")
    private VehicleModelResponse model;

    @Schema(description = "Body type information")
    private BodyTypeResponse bodyType;

    @Schema(description = "Category information")
    private AdCategoryResponse category;

    @Schema(description = "Store information if posted by a dealership")
    private StoreResponse store;

    @Schema(description = "Manufacturing year", example = "2020")
    private Integer year;

    @Schema(description = "Vehicle mileage in kilometers", example = "45000")
    private Integer mileage;

    @Schema(description = "Fuel type", example = "Gasoline")
    private String fuelType;

    @Schema(description = "Transmission type", example = "Automatic")
    private String transmission;

    @Schema(description = "Vehicle condition", example = "Excellent")
    private String condition;

    @Schema(description = "Vehicle price", example = "25000.00")
    private BigDecimal price;

    @Schema(description = "Currency code", example = "USD")
    private String currency;

    @Schema(description = "Country", example = "United States")
    private String country;

    @Schema(description = "City", example = "Los Angeles")
    private String city;

    @Schema(description = "Latitude", example = "34.0522")
    private BigDecimal latitude;

    @Schema(description = "Longitude", example = "-118.2437")
    private BigDecimal longitude;

    @Schema(description = "Full address", example = "123 Main Street, Los Angeles, CA 90001")
    private String address;

    @Schema(description = "Advertisement status", example = "ACTIVE")
    private String status;

    @Schema(description = "Bidding enabled flag", example = "false")
    private Boolean isBiddingEnabled;

    @Schema(description = "Pre-order enabled flag", example = "false")
    private Boolean isPreorderEnabled;
}
