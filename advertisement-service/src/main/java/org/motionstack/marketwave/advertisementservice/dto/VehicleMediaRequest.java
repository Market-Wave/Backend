package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request DTO for vehicle media")
public class VehicleMediaRequest {

    @NotNull(message = "Ad ID is required")
    @Schema(description = "Vehicle ad ID", example = "1")
    private Long adId;

    @NotBlank(message = "URL is required")
    @Schema(description = "Media URL", example = "https://storage.example.com/vehicles/img001.jpg")
    private String url;

    @NotBlank(message = "Media type is required")
    @Schema(description = "Media type", example = "IMAGE", allowableValues = {"IMAGE", "VIDEO"})
    private String mediaType;

    @NotBlank(message = "Media view is required")
    @Schema(description = "Media view/angle", example = "EXTERIOR", 
            allowableValues = {"EXTERIOR", "INTERIOR", "DASHBOARD", "ENGINE", "TRUNK", "WHEELS"})
    private String mediaView;

    @Schema(description = "Sort order for display", example = "0")
    private Integer sortOrder;
}
