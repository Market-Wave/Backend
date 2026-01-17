package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request DTO for creating/updating a body type")
public class BodyTypeRequest {

    @NotBlank(message = "Body type name is required")
    @Schema(description = "Body type name", example = "Sedan")
    private String name;
}
