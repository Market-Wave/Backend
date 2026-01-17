package org.motionstack.marketwave.advertisementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request DTO for store page configuration")
public class StorePageConfigRequest {

    @NotNull(message = "Store ID is required")
    @Schema(description = "Store ID", example = "1")
    private Long storeId;

    @Size(max = 200)
    @Schema(description = "SEO meta title", example = "Premium Auto Dealers - Quality Vehicles")
    private String metaTitle;

    @Size(max = 500)
    @Schema(description = "SEO meta description", example = "Browse our collection of quality vehicles with competitive pricing.")
    private String metaDescription;

    @Size(max = 100)
    @Schema(description = "Theme name", example = "modern-dark")
    private String themeName;

    @Size(max = 50)
    @Schema(description = "Theme version", example = "1.0.0")
    private String themeVersion;

    @Size(max = 1000)
    @Schema(description = "Color configuration JSON", 
            example = "{\"primary\":\"#007bff\",\"secondary\":\"#6c757d\",\"accent\":\"#28a745\"}")
    private String colors;

    @Size(max = 500)
    @Schema(description = "Button styles JSON", 
            example = "{\"borderRadius\":\"4px\",\"padding\":\"10px 20px\"}")
    private String buttons;

    @Size(max = 1000)
    @Schema(description = "Typography configuration JSON", 
            example = "{\"fontFamily\":\"Roboto, sans-serif\",\"fontSize\":\"16px\"}")
    private String typography;

    @Size(max = 500)
    @Schema(description = "Layout configuration JSON", 
            example = "{\"header\":\"fixed\",\"sidebar\":\"left\"}")
    private String layout;

    @Size(max = 1000)
    @Schema(description = "Pages configuration JSON", 
            example = "{\"home\":true,\"about\":true,\"contact\":true}")
    private String pages;

    @Size(max = 1000)
    @Schema(description = "Sections configuration JSON", 
            example = "{\"hero\":true,\"featured\":true,\"testimonials\":false}")
    private String sections;
}
