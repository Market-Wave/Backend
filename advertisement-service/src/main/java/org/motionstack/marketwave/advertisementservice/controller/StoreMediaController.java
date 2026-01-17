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
import org.motionstack.marketwave.advertisementservice.dto.StoreMediaRequest;
import org.motionstack.marketwave.advertisementservice.dto.StoreMediaResponse;
import org.motionstack.marketwave.advertisementservice.model.StoreMedia;
import org.motionstack.marketwave.advertisementservice.service.StoreMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/store-media")
@Tag(name = "Store Media", description = "Endpoints for managing store media (logos, banners, images)")
public class StoreMediaController {

    private final StoreMediaService mediaService;
    private final DtoMapper dtoMapper;

    public StoreMediaController(StoreMediaService mediaService, DtoMapper dtoMapper) {
        this.mediaService = mediaService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Add media to store", description = "Adds a new media file to a store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Media added successfully",
                    content = @Content(schema = @Schema(implementation = StoreMediaResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<StoreMediaResponse> addMedia(@Valid @RequestBody StoreMediaRequest request) {
        StoreMedia entity = dtoMapper.toEntity(request);
        StoreMedia created = mediaService.addMedia(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get media by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Media found"),
        @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<StoreMediaResponse> getMediaById(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id) {
        return mediaService.getMediaById(id)
                .map(media -> ResponseEntity.ok(dtoMapper.toResponse(media)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "Get all media for a store", description = "Retrieves all media files for a specific store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of media retrieved")
    })
    public ResponseEntity<List<StoreMediaResponse>> getMediaForStore(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId) {
        List<StoreMediaResponse> responses = mediaService.getMediaForStore(storeId).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/store/{storeId}/type/{mediaType}")
    @Operation(summary = "Get media by type", description = "Retrieves media filtered by type (IMAGE, VIDEO, LOGO, BANNER)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of media retrieved")
    })
    public ResponseEntity<List<StoreMediaResponse>> getMediaByType(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId,
            @Parameter(description = "Media type", example = "LOGO") @PathVariable String mediaType) {
        List<StoreMediaResponse> responses = mediaService.getMediaByType(storeId, mediaType).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/store/{storeId}/view/{mediaView}")
    @Operation(summary = "Get media by view", description = "Retrieves media filtered by view (STOREFRONT, INTERIOR, etc.)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of media retrieved")
    })
    public ResponseEntity<List<StoreMediaResponse>> getMediaByView(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId,
            @Parameter(description = "Media view", example = "STOREFRONT") @PathVariable String mediaView) {
        List<StoreMediaResponse> responses = mediaService.getMediaByView(storeId, mediaView).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update media")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Media updated successfully"),
        @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<StoreMediaResponse> updateMedia(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody StoreMediaRequest request) {
        StoreMedia entity = dtoMapper.toEntity(request);
        StoreMedia updated = mediaService.updateMedia(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/sort-order")
    @Operation(summary = "Update media sort order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sort order updated successfully"),
        @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<StoreMediaResponse> updateSortOrder(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id,
            @Parameter(description = "New sort order", example = "0") @RequestParam Integer sortOrder) {
        StoreMedia updated = mediaService.updateSortOrder(id, sortOrder);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete media")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Media deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<Void> deleteMedia(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/store/{storeId}")
    @Operation(summary = "Delete all media for a store", description = "Deletes all media files for a specific store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "All media deleted successfully")
    })
    public ResponseEntity<Void> deleteAllMediaForStore(
            @Parameter(description = "Store ID", example = "1") @PathVariable Long storeId) {
        mediaService.deleteAllMediaForStore(storeId);
        return ResponseEntity.noContent().build();
    }
}
