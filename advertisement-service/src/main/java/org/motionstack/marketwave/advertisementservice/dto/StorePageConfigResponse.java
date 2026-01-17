package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response DTO for store page configuration")
public class StorePageConfigResponse {

    @Schema(description = "Configuration ID", example = "1")
    private Long id;

    @Schema(description = "Store ID", example = "1")
    private Long storeId;

    @Schema(description = "SEO meta title", example = "Premium Auto Dealers - Quality Vehicles")
    private String metaTitle;

    @Schema(description = "SEO meta description", example = "Browse our collection of quality vehicles.")
    private String metaDescription;

    @Schema(description = "Theme name", example = "modern-dark")
    private String themeName;

    @Schema(description = "Theme version", example = "1.0.0")
    private String themeVersion;

    @Schema(description = "Color configuration JSON", example = "{\"primary\":\"#007bff\"}")
    private String colors;

    @Schema(description = "Button styles JSON", example = "{\"borderRadius\":\"4px\"}")
    private String buttons;

    @Schema(description = "Typography configuration JSON", example = "{\"fontFamily\":\"Roboto\"}")
    private String typography;

    @Schema(description = "Layout configuration JSON", example = "{\"header\":\"fixed\"}")
    private String layout;

    @Schema(description = "Pages configuration JSON", example = "{\"home\":true}")
    private String pages;

    @Schema(description = "Sections configuration JSON", example = "{\"hero\":true}")
    private String sections;
}
