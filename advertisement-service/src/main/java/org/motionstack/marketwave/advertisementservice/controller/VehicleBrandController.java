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
import org.motionstack.marketwave.advertisementservice.dto.VehicleBrandRequest;
import org.motionstack.marketwave.advertisementservice.dto.VehicleBrandResponse;
import org.motionstack.marketwave.advertisementservice.model.VehicleBrand;
import org.motionstack.marketwave.advertisementservice.service.VehicleBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vehicle-brands")
@Tag(name = "Vehicle Brands", description = "Endpoints for managing vehicle brands")
public class VehicleBrandController {

    private final VehicleBrandService brandService;
    private final DtoMapper dtoMapper;

    public VehicleBrandController(VehicleBrandService brandService, DtoMapper dtoMapper) {
        this.brandService = brandService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Brand created successfully",
                    content = @Content(schema = @Schema(implementation = VehicleBrandResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<VehicleBrandResponse> createBrand(@Valid @RequestBody VehicleBrandRequest request) {
        VehicleBrand entity = dtoMapper.toEntity(request);
        VehicleBrand created = brandService.createBrand(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle brand by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand found",
                    content = @Content(schema = @Schema(implementation = VehicleBrandResponse.class))),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<VehicleBrandResponse> getBrandById(
            @Parameter(description = "Brand ID", example = "1") @PathVariable Long id) {
        return brandService.getBrandById(id)
                .map(brand -> ResponseEntity.ok(dtoMapper.toResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all vehicle brands")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of brands retrieved")
    })
    public ResponseEntity<List<VehicleBrandResponse>> getAllBrands() {
        List<VehicleBrandResponse> responses = brandService.getAllBrands().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand updated successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<VehicleBrandResponse> updateBrand(
            @Parameter(description = "Brand ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody VehicleBrandRequest request) {
        VehicleBrand entity = dtoMapper.toEntity(request);
        VehicleBrand updated = brandService.updateBrand(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Brand deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<Void> deleteBrand(
            @Parameter(description = "Brand ID", example = "1") @PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
