package org.motionstack.marketwave.advertisementservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.motionstack.marketwave.advertisementservice.dto.BodyTypeRequest;
import org.motionstack.marketwave.advertisementservice.dto.BodyTypeResponse;
import org.motionstack.marketwave.advertisementservice.dto.DtoMapper;
import org.motionstack.marketwave.advertisementservice.model.BodyType;
import org.motionstack.marketwave.advertisementservice.service.BodyTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/body-types")
@Tag(name = "Body Types", description = "Endpoints for managing vehicle body types")
public class BodyTypeController {

    private final BodyTypeService bodyTypeService;
    private final DtoMapper dtoMapper;

    public BodyTypeController(BodyTypeService bodyTypeService, DtoMapper dtoMapper) {
        this.bodyTypeService = bodyTypeService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new body type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Body type created successfully",
                    content = @Content(schema = @Schema(implementation = BodyTypeResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<BodyTypeResponse> createBodyType(@Valid @RequestBody BodyTypeRequest request) {
        BodyType entity = dtoMapper.toEntity(request);
        BodyType created = bodyTypeService.createBodyType(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get body type by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Body type found"),
        @ApiResponse(responseCode = "404", description = "Body type not found")
    })
    public ResponseEntity<BodyTypeResponse> getBodyTypeById(
            @Parameter(description = "Body type ID", example = "1") @PathVariable Long id) {
        return bodyTypeService.getBodyTypeById(id)
                .map(bodyType -> ResponseEntity.ok(dtoMapper.toResponse(bodyType)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all body types")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of body types retrieved")
    })
    public ResponseEntity<List<BodyTypeResponse>> getAllBodyTypes() {
        List<BodyTypeResponse> responses = bodyTypeService.getAllBodyTypes().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update body type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Body type updated successfully"),
        @ApiResponse(responseCode = "404", description = "Body type not found")
    })
    public ResponseEntity<BodyTypeResponse> updateBodyType(
            @Parameter(description = "Body type ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody BodyTypeRequest request) {
        BodyType entity = dtoMapper.toEntity(request);
        BodyType updated = bodyTypeService.updateBodyType(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete body type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Body type deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Body type not found")
    })
    public ResponseEntity<Void> deleteBodyType(
            @Parameter(description = "Body type ID", example = "1") @PathVariable Long id) {
        bodyTypeService.deleteBodyType(id);
        return ResponseEntity.noContent().build();
    }
}
