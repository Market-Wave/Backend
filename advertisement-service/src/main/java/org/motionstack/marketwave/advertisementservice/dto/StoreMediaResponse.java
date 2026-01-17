package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response DTO for store media")
public class StoreMediaResponse {

    @Schema(description = "Media ID", example = "1")
    private Long id;

    @Schema(description = "Store ID", example = "1")
    private Long storeId;

    @Schema(description = "Media URL", example = "https://storage.example.com/stores/logo001.png")
    private String url;

    @Schema(description = "Media type", example = "LOGO")
    private String mediaType;

    @Schema(description = "Media view/purpose", example = "LOGO")
    private String mediaView;

    @Schema(description = "Sort order", example = "0")
    private Integer sortOrder;
}
