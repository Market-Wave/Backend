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
import org.motionstack.marketwave.advertisementservice.dto.StoreRequest;
import org.motionstack.marketwave.advertisementservice.dto.StoreResponse;
import org.motionstack.marketwave.advertisementservice.model.Store;
import org.motionstack.marketwave.advertisementservice.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stores")
@Tag(name = "Stores", description = "Endpoints for managing dealership stores")
public class StoreController {

    private final StoreService storeService;
    private final DtoMapper dtoMapper;

    public StoreController(StoreService storeService, DtoMapper dtoMapper) {
        this.storeService = storeService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new store", description = "Creates a new dealership store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Store created successfully",
                    content = @Content(schema = @Schema(implementation = StoreResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<StoreResponse> createStore(@Valid @RequestBody StoreRequest request) {
        Store entity = dtoMapper.toEntity(request);
        Store created = storeService.createStore(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get store by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Store found"),
        @ApiResponse(responseCode = "404", description = "Store not found")
    })
    public ResponseEntity<StoreResponse> getStoreById(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long id) {
        return storeService.getStoreById(id)
                .map(store -> ResponseEntity.ok(dtoMapper.toResponse(store)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all stores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of stores retrieved")
    })
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        List<StoreResponse> responses = storeService.getAllStores().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get stores by owner", description = "Retrieves all stores for a specific owner")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of owner's stores")
    })
    public ResponseEntity<List<StoreResponse>> getStoresByOwner(
            @Parameter(description = "Owner UUID", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID ownerId) {
        List<StoreResponse> responses = storeService.getStoresByOwner(ownerId).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get store by slug", description = "Retrieves a store by its unique slug")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Store found"),
        @ApiResponse(responseCode = "404", description = "Store not found")
    })
    public ResponseEntity<StoreResponse> getStoreBySlug(
            @Parameter(description = "Store slug", example = "premium-auto-dealers")
            @PathVariable String slug) {
        return storeService.getStoreBySlug(slug)
                .map(store -> ResponseEntity.ok(dtoMapper.toResponse(store)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Store updated successfully"),
        @ApiResponse(responseCode = "404", description = "Store not found")
    })
    public ResponseEntity<StoreResponse> updateStore(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody StoreRequest request) {
        Store entity = dtoMapper.toEntity(request);
        Store updated = storeService.updateStore(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update store status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status updated successfully"),
        @ApiResponse(responseCode = "404", description = "Store not found")
    })
    public ResponseEntity<StoreResponse> updateStoreStatus(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long id,
            @Parameter(description = "New status", example = "ACTIVE") @RequestParam String status) {
        Store updated = storeService.updateStoreStatus(id, status);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Store deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Store not found")
    })
    public ResponseEntity<Void> deleteStore(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
