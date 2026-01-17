package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response DTO for vehicle brand")
public class VehicleBrandResponse {

    @Schema(description = "Brand ID", example = "1")
    private Long id;

    @Schema(description = "Brand name", example = "Toyota")
    private String name;
}
