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
import org.motionstack.marketwave.advertisementservice.dto.VehicleModelRequest;
import org.motionstack.marketwave.advertisementservice.dto.VehicleModelResponse;
import org.motionstack.marketwave.advertisementservice.model.VehicleModel;
import org.motionstack.marketwave.advertisementservice.service.VehicleModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vehicle-models")
@Tag(name = "Vehicle Models", description = "Endpoints for managing vehicle models")
public class VehicleModelController {

    private final VehicleModelService modelService;
    private final DtoMapper dtoMapper;

    public VehicleModelController(VehicleModelService modelService, DtoMapper dtoMapper) {
        this.modelService = modelService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle model")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Model created successfully",
                    content = @Content(schema = @Schema(implementation = VehicleModelResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<VehicleModelResponse> createModel(@Valid @RequestBody VehicleModelRequest request) {
        VehicleModel entity = dtoMapper.toEntity(request);
        VehicleModel created = modelService.createModel(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle model by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Model found"),
        @ApiResponse(responseCode = "404", description = "Model not found")
    })
    public ResponseEntity<VehicleModelResponse> getModelById(
            @Parameter(description = "Model ID", example = "1") @PathVariable Long id) {
        return modelService.getModelById(id)
                .map(model -> ResponseEntity.ok(dtoMapper.toResponse(model)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all vehicle models")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of models retrieved")
    })
    public ResponseEntity<List<VehicleModelResponse>> getAllModels() {
        List<VehicleModelResponse> responses = modelService.getAllModels().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/brand/{brandId}")
    @Operation(summary = "Get models by brand", description = "Retrieves all models for a specific brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of brand models retrieved")
    })
    public ResponseEntity<List<VehicleModelResponse>> getModelsByBrand(
            @Parameter(description = "Brand ID", example = "1") @PathVariable Long brandId) {
        List<VehicleModelResponse> responses = modelService.getModelsByBrand(brandId).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle model")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Model updated successfully"),
        @ApiResponse(responseCode = "404", description = "Model not found")
    })
    public ResponseEntity<VehicleModelResponse> updateModel(
            @Parameter(description = "Model ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody VehicleModelRequest request) {
        VehicleModel entity = dtoMapper.toEntity(request);
        VehicleModel updated = modelService.updateModel(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle model")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Model deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Model not found")
    })
    public ResponseEntity<Void> deleteModel(
            @Parameter(description = "Model ID", example = "1") @PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
