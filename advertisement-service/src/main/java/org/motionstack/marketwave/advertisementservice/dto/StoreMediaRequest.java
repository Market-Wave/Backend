package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request DTO for store media")
public class StoreMediaRequest {

    @NotNull(message = "Store ID is required")
    @Schema(description = "Store ID", example = "1")
    private Long storeId;

    @NotBlank(message = "URL is required")
    @Schema(description = "Media URL", example = "https://storage.example.com/stores/logo001.png")
    private String url;

    @NotBlank(message = "Media type is required")
    @Schema(description = "Media type", example = "IMAGE", allowableValues = {"IMAGE", "VIDEO", "LOGO", "BANNER"})
    private String mediaType;

    @NotBlank(message = "Media view is required")
    @Schema(description = "Media view/purpose", example = "LOGO", 
            allowableValues = {"STOREFRONT", "INTERIOR", "LOGO", "BANNER", "GALLERY"})
    private String mediaView;

    @Schema(description = "Sort order for display", example = "0")
    private Integer sortOrder;
}
