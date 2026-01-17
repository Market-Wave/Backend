package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response DTO for vehicle media")
public class VehicleMediaResponse {

    @Schema(description = "Media ID", example = "1")
    private Long id;

    @Schema(description = "Vehicle ad ID", example = "1")
    private Long adId;

    @Schema(description = "Media URL", example = "https://storage.example.com/vehicles/img001.jpg")
    private String url;

    @Schema(description = "Media type", example = "IMAGE")
    private String mediaType;

    @Schema(description = "Media view/angle", example = "EXTERIOR")
    private String mediaView;

    @Schema(description = "Sort order", example = "0")
    private Integer sortOrder;
}
