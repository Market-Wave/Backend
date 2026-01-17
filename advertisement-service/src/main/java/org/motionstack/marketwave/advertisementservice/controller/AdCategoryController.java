package org.motionstack.marketwave.advertisementservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.motionstack.marketwave.advertisementservice.dto.AdCategoryRequest;
import org.motionstack.marketwave.advertisementservice.dto.AdCategoryResponse;
import org.motionstack.marketwave.advertisementservice.dto.DtoMapper;
import org.motionstack.marketwave.advertisementservice.model.AdCategory;
import org.motionstack.marketwave.advertisementservice.service.AdCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ad-categories")
@Tag(name = "Ad Categories", description = "Endpoints for managing advertisement categories")
public class AdCategoryController {

    private final AdCategoryService categoryService;
    private final DtoMapper dtoMapper;

    public AdCategoryController(AdCategoryService categoryService, DtoMapper dtoMapper) {
        this.categoryService = categoryService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new ad category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Category created successfully",
                    content = @Content(schema = @Schema(implementation = AdCategoryResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<AdCategoryResponse> createCategory(@Valid @RequestBody AdCategoryRequest request) {
        AdCategory entity = dtoMapper.toEntity(request);
        AdCategory created = categoryService.createCategory(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category found"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<AdCategoryResponse> getCategoryById(
            @Parameter(description = "Category ID", example = "1") @PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(dtoMapper.toResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all ad categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of categories retrieved")
    })
    public ResponseEntity<List<AdCategoryResponse>> getAllCategories() {
        List<AdCategoryResponse> responses = categoryService.getAllCategories().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update ad category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category updated successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<AdCategoryResponse> updateCategory(
            @Parameter(description = "Category ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody AdCategoryRequest request) {
        AdCategory entity = dtoMapper.toEntity(request);
        AdCategory updated = categoryService.updateCategory(id, entity);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ad category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "Category ID", example = "1") @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
