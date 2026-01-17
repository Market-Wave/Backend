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
import org.motionstack.marketwave.advertisementservice.dto.VehicleAdRequest;
import org.motionstack.marketwave.advertisementservice.dto.VehicleAdResponse;
import org.motionstack.marketwave.advertisementservice.model.VehicleAd;
import org.motionstack.marketwave.advertisementservice.service.VehicleAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vehicle-ads")
@Tag(name = "Vehicle Advertisements", description = "Endpoints for managing vehicle advertisements")
public class VehicleAdController {

    private final VehicleAdService vehicleAdService;
    private final DtoMapper dtoMapper;

    public VehicleAdController(VehicleAdService vehicleAdService, DtoMapper dtoMapper) {
        this.vehicleAdService = vehicleAdService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle advertisement", 
               description = "Creates a new vehicle advertisement with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Advertisement created successfully",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<VehicleAdResponse> createAd(
            @Valid @RequestBody VehicleAdRequest request) {
        VehicleAd entity = dtoMapper.toEntity(request);
        VehicleAd created = vehicleAdService.createAd(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle ad by ID", 
               description = "Retrieves a single vehicle advertisement by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Advertisement found",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class))),
        @ApiResponse(responseCode = "404", description = "Advertisement not found")
    })
    public ResponseEntity<VehicleAdResponse> getAdById(
            @Parameter(description = "Advertisement ID", example = "1")
            @PathVariable Long id) {
        return vehicleAdService.getAdById(id)
                .map(ad -> ResponseEntity.ok(dtoMapper.toResponse(ad)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all vehicle ads", 
               description = "Retrieves all vehicle advertisements")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of advertisements retrieved",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class)))
    })
    public ResponseEntity<List<VehicleAdResponse>> getAllAds() {
        List<VehicleAdResponse> responses = vehicleAdService.getAllAds().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get ads by owner", 
               description = "Retrieves all vehicle advertisements for a specific owner")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of owner's advertisements",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class)))
    })
    public ResponseEntity<List<VehicleAdResponse>> getAdsByOwner(
            @Parameter(description = "Owner UUID", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID ownerId) {
        List<VehicleAdResponse> responses = vehicleAdService.getAdsByOwner(ownerId).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Get ads by store", 
               description = "Retrieves all vehicle advertisements for a specific store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of store's advertisements",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class)))
    })
    public ResponseEntity<List<VehicleAdResponse>> getAdsByStore(
            @Parameter(description = "Store ID", example = "1")
            @PathVariable Long storeId) {
        List<VehicleAdResponse> responses = vehicleAdService.getAdsByStore(storeId).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle ad", 
               description = "Updates an existing vehicle advertisement")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Advertisement updated successfully",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class))),
        @ApiResponse(responseCode = "404", description = "Advertisement not found"),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<VehicleAdResponse> updateAd(
            @Parameter(description = "Advertisement ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody VehicleAdRequest request) {
        VehicleAd entity = dtoMapper.toEntity(request);
        VehicleAd updated = vehicleAdService.updateAd(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update ad status", 
               description = "Updates the status of a vehicle advertisement")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status updated successfully",
                    content = @Content(schema = @Schema(implementation = VehicleAdResponse.class))),
        @ApiResponse(responseCode = "404", description = "Advertisement not found")
    })
    public ResponseEntity<VehicleAdResponse> updateStatus(
            @Parameter(description = "Advertisement ID", example = "1")
            @PathVariable Long id,
            @Parameter(description = "New status", example = "ACTIVE")
            @RequestParam String status) {
        VehicleAd updated = vehicleAdService.updateAdStatus(id, status);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle ad", 
               description = "Deletes a vehicle advertisement")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Advertisement deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Advertisement not found")
    })
    public ResponseEntity<Void> deleteAd(
            @Parameter(description = "Advertisement ID", example = "1")
            @PathVariable Long id) {
        vehicleAdService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    @Operation(summary = "Check if ad exists", 
               description = "Checks whether a vehicle advertisement exists")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Existence check completed")
    })
    public ResponseEntity<Boolean> adExists(
            @Parameter(description = "Advertisement ID", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(vehicleAdService.adExists(id));
    }
}
