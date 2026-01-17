package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request DTO for creating/updating a vehicle model")
public class VehicleModelRequest {

    @NotNull(message = "Brand ID is required")
    @Schema(description = "Vehicle brand ID", example = "1")
    private Long brandId;

    @NotBlank(message = "Model name is required")
    @Schema(description = "Model name", example = "Corolla")
    private String name;
}
