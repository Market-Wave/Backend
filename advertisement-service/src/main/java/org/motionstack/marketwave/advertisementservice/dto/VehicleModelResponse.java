package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response DTO for vehicle model")
public class VehicleModelResponse {

    @Schema(description = "Model ID", example = "1")
    private Long id;

    @Schema(description = "Brand ID", example = "1")
    private Long brandId;

    @Schema(description = "Model name", example = "Corolla")
    private String name;
}
