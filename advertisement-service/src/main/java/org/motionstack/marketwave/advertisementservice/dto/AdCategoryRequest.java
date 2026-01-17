package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request DTO for creating/updating an ad category")
public class AdCategoryRequest {

    @NotBlank(message = "Category name is required")
    @Schema(description = "Category name", example = "Cars")
    private String name;
}
