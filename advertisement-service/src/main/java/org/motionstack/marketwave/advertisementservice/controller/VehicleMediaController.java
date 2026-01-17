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
import org.motionstack.marketwave.advertisementservice.dto.VehicleMediaRequest;
import org.motionstack.marketwave.advertisementservice.dto.VehicleMediaResponse;
import org.motionstack.marketwave.advertisementservice.model.VehicleMedia;
import org.motionstack.marketwave.advertisementservice.service.VehicleMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vehicle-media")
@Tag(name = "Vehicle Media", description = "Endpoints for managing vehicle advertisement media (images, videos)")
public class VehicleMediaController {

    private final VehicleMediaService mediaService;
    private final DtoMapper dtoMapper;

    public VehicleMediaController(VehicleMediaService mediaService, DtoMapper dtoMapper) {
        this.mediaService = mediaService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Add media to vehicle ad", description = "Adds a new media file to a vehicle advertisement")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Media added successfully",
                    content = @Content(schema = @Schema(implementation = VehicleMediaResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<VehicleMediaResponse> addMedia(@Valid @RequestBody VehicleMediaRequest request) {
        VehicleMedia entity = dtoMapper.toEntity(request);
        VehicleMedia created = mediaService.addMedia(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get media by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Media found"),
        @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<VehicleMediaResponse> getMediaById(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id) {
        return mediaService.getMediaById(id)
                .map(media -> ResponseEntity.ok(dtoMapper.toResponse(media)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ad/{adId}")
    @Operation(summary = "Get all media for a vehicle ad", description = "Retrieves all media files for a specific ad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of media retrieved")
    })
    public ResponseEntity<List<VehicleMediaResponse>> getMediaForAd(
            @Parameter(description = "Ad ID", example = "1") @PathVariable Long adId) {
        List<VehicleMediaResponse> responses = mediaService.getMediaForAd(adId).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/ad/{adId}/type/{mediaType}")
    @Operation(summary = "Get media by type", description = "Retrieves media filtered by type (IMAGE, VIDEO)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of media retrieved")
    })
    public ResponseEntity<List<VehicleMediaResponse>> getMediaByType(
            @Parameter(description = "Ad ID", example = "1") @PathVariable Long adId,
            @Parameter(description = "Media type", example = "IMAGE") @PathVariable String mediaType) {
        List<VehicleMediaResponse> responses = mediaService.getMediaByType(adId, mediaType).stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/ad/{adId}/view/{mediaView}")
    @Operation(summary = "Get media by view", description = "Retrieves media filtered by view (EXTERIOR, INTERIOR, etc.)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of media retrieved")
    })
    public ResponseEntity<List<VehicleMediaResponse>> getMediaByView(
            @Parameter(description = "Ad ID", example = "1") @PathVariable Long adId,
            @Parameter(description = "Media view", example = "EXTERIOR") @PathVariable String mediaView) {
        List<VehicleMediaResponse> responses = mediaService.getMediaByView(adId, mediaView).stream()
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
    public ResponseEntity<VehicleMediaResponse> updateMedia(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody VehicleMediaRequest request) {
        VehicleMedia entity = dtoMapper.toEntity(request);
        VehicleMedia updated = mediaService.updateMedia(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/sort-order")
    @Operation(summary = "Update media sort order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sort order updated successfully"),
        @ApiResponse(responseCode = "404", description = "Media not found")
    })
    public ResponseEntity<VehicleMediaResponse> updateSortOrder(
            @Parameter(description = "Media ID", example = "1") @PathVariable Long id,
            @Parameter(description = "New sort order", example = "0") @RequestParam Integer sortOrder) {
        VehicleMedia updated = mediaService.updateSortOrder(id, sortOrder);
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

    @DeleteMapping("/ad/{adId}")
    @Operation(summary = "Delete all media for an ad", description = "Deletes all media files for a specific ad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "All media deleted successfully")
    })
    public ResponseEntity<Void> deleteAllMediaForAd(
            @Parameter(description = "Ad ID", example = "1") @PathVariable Long adId) {
        mediaService.deleteAllMediaForAd(adId);
        return ResponseEntity.noContent().build();
    }
}
