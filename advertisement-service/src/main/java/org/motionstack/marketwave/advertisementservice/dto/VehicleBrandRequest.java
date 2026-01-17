package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request DTO for creating/updating a vehicle brand")
public class VehicleBrandRequest {

    @NotBlank(message = "Brand name is required")
    @Schema(description = "Brand name", example = "Toyota")
    private String name;
}
