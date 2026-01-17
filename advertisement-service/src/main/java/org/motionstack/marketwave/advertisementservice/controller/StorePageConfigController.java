package org.motionstack.marketwave.advertisementservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.motionstack.marketwave.advertisementservice.dto.DtoMapper;
import org.motionstack.marketwave.advertisementservice.dto.StorePageConfigRequest;
import org.motionstack.marketwave.advertisementservice.dto.StorePageConfigResponse;
import org.motionstack.marketwave.advertisementservice.model.StorePageConfig;
import org.motionstack.marketwave.advertisementservice.service.StorePageConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/store-configs")
@Tag(name = "Store Page Configurations", description = "Endpoints for managing store page customization and themes")
public class StorePageConfigController {

    private final StorePageConfigService configService;
    private final DtoMapper dtoMapper;

    public StorePageConfigController(StorePageConfigService configService, DtoMapper dtoMapper) {
        this.configService = configService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create store page configuration", description = "Creates a new configuration for a store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Configuration created successfully",
                    content = @Content(schema = @Schema(implementation = StorePageConfigResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<StorePageConfigResponse> createConfig(@Valid @RequestBody StorePageConfigRequest request) {
        StorePageConfig entity = dtoMapper.toEntity(request);
        StorePageConfig created = configService.createConfig(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get configuration by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Configuration found"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<StorePageConfigResponse> getConfigById(
            @Parameter(description = "Configuration ID", example = "1") @PathVariable Long id) {
        return configService.getConfigById(id)
                .map(config -> ResponseEntity.ok(dtoMapper.toResponse(config)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Get configuration by store ID", description = "Retrieves the configuration for a specific store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Configuration found"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<StorePageConfigResponse> getConfigByStoreId(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId) {
        return configService.getConfigByStoreId(storeId)
                .map(config -> ResponseEntity.ok(dtoMapper.toResponse(config)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all configurations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of configurations retrieved")
    })
    public ResponseEntity<List<StorePageConfigResponse>> getAllConfigs() {
        List<StorePageConfigResponse> responses = configService.getAllConfigs().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update configuration")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Configuration updated successfully"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<StorePageConfigResponse> updateConfig(
            @Parameter(description = "Configuration ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody StorePageConfigRequest request) {
        StorePageConfig entity = dtoMapper.toEntity(request);
        StorePageConfig updated = configService.updateConfig(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/store/{storeId}/theme")
    @Operation(summary = "Update store theme", description = "Updates theme settings for a store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Theme updated successfully"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<StorePageConfigResponse> updateTheme(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId,
            @Parameter(description = "Theme name", example = "modern-dark") @RequestParam String themeName,
            @Parameter(description = "Theme version", example = "1.0.0") @RequestParam String themeVersion) {
        StorePageConfig updated = configService.updateTheme(storeId, themeName, themeVersion);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/store/{storeId}/colors")
    @Operation(summary = "Update color scheme", description = "Updates the color configuration for a store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Colors updated successfully"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<StorePageConfigResponse> updateColors(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId,
            @Parameter(description = "Colors JSON", example = "{\"primary\":\"#007bff\"}") @RequestBody String colors) {
        StorePageConfig updated = configService.updateColors(storeId, colors);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/store/{storeId}/layout")
    @Operation(summary = "Update layout settings", description = "Updates the layout configuration for a store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Layout updated successfully"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<StorePageConfigResponse> updateLayout(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId,
            @Parameter(description = "Layout JSON", example = "{\"header\":\"fixed\"}") @RequestBody String layout) {
        StorePageConfig updated = configService.updateLayout(storeId, layout);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete configuration")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Configuration deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Configuration not found")
    })
    public ResponseEntity<Void> deleteConfig(
            @Parameter(description = "Configuration ID", example = "1") @PathVariable Long id) {
        configService.deleteConfig(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/store/{storeId}")
    @Operation(summary = "Delete configuration by store ID", description = "Deletes configuration for a specific store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Configuration deleted successfully")
    })
    public ResponseEntity<Void> deleteConfigByStoreId(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId) {
        configService.deleteConfigByStoreId(storeId);
        return ResponseEntity.noContent().build();
    }
}
