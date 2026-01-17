package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response DTO for body type")
public class BodyTypeResponse {

    @Schema(description = "Body type ID", example = "1")
    private Long id;

    @Schema(description = "Body type name", example = "Sedan")
    private String name;
}
