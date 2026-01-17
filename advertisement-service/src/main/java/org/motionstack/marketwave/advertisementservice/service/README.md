# Service Layer Documentation

## Overview

The **service folder** contains the business logic layer of the Advertisement Service. Services act as an intermediary between controllers (HTTP layer) and repositories (data access layer), implementing business rules, validations, and complex operations.

## Purpose

- Implement business logic and rules
- Coordinate operations across multiple repositories
- Provide transaction management
- Validate data before persistence
- Transform data between layers
- Keep controllers thin and focused on HTTP concerns

## Application Flow

```
Client Request → Controller → Service → Repository → Database
                                  ↓
                           Business Logic
                           Validation
                           Transactions
```

## Service Classes

### VehicleAdService

Manages vehicle advertisement lifecycle and business operations.

**Key Methods:**
- `createAd(VehicleAd)` - Creates a new vehicle ad with default status
- `getAdById(Long)` - Retrieves ad by ID
- `getAllAds()` - Retrieves all vehicle ads
- `updateAd(Long, VehicleAd)` - Updates existing ad
- `deleteAd(Long)` - Deletes an ad
- `getAdsByOwner(UUID)` - Finds all ads by owner
- `updateAdStatus(Long, String)` - Changes ad status (DRAFT, ACTIVE, INACTIVE, SOLD)
- `adExists(Long)` - Checks if ad exists

**Business Rules:**
- Sets default status to "DRAFT" if not provided
- Preserves owner ID during updates
- Validates ad exists before updates

**Usage Example:**
```java
@RestController
@RequestMapping("/api/ads")
public class VehicleAdController {
    private final VehicleAdService vehicleAdService;
    
    @PostMapping
    public ResponseEntity<VehicleAd> createAd(@RequestBody VehicleAd ad) {
        VehicleAd created = vehicleAdService.createAd(ad);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<VehicleAd> updateStatus(
            @PathVariable Long id, 
            @RequestParam String status) {
        VehicleAd updated = vehicleAdService.updateAdStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
```

### VehicleBrandService

Manages vehicle brand data.

**Key Methods:**
- `createBrand(VehicleBrand)` - Creates a new brand
- `getBrandById(Long)` - Retrieves brand by ID
- `getAllBrands()` - Retrieves all brands
- `updateBrand(Long, VehicleBrand)` - Updates existing brand
- `deleteBrand(Long)` - Deletes a brand
- `brandExists(Long)` - Checks if brand exists

**Business Rules:**
- Enforces unique brand names (via entity constraint)
- Validates brand exists before updates

### VehicleModelService

Manages vehicle models and their relationships with brands.

**Key Methods:**
- `createModel(VehicleModel)` - Creates a new model
- `getModelById(Long)` - Retrieves model by ID
- `getAllModels()` - Retrieves all models
- `getModelsByBrand(Long)` - Retrieves all models for a brand
- `updateModel(Long, VehicleModel)` - Updates existing model
- `deleteModel(Long)` - Deletes a model
- `modelExists(Long)` - Checks if model exists

**Business Rules:**
- Associates models with brands via brandId
- Filters models by brand for hierarchical data

### BodyTypeService

Manages vehicle body type definitions.

**Key Methods:**
- `createBodyType(BodyType)` - Creates a new body type
- `getBodyTypeById(Long)` - Retrieves body type by ID
- `getAllBodyTypes()` - Retrieves all body types
- `updateBodyType(Long, BodyType)` - Updates existing body type
- `deleteBodyType(Long)` - Deletes a body type
- `bodyTypeExists(Long)` - Checks if body type exists

**Business Rules:**
- Enforces unique body type names (via entity constraint)
- Provides reference data for vehicle classification

### AdCategoryService

Manages advertisement category definitions.

**Key Methods:**
- `createCategory(AdCategory)` - Creates a new category
- `getCategoryById(Long)` - Retrieves category by ID
- `getAllCategories()` - Retrieves all categories
- `updateCategory(Long, AdCategory)` - Updates existing category
- `deleteCategory(Long)` - Deletes a category
- `categoryExists(Long)` - Checks if category exists

**Business Rules:**
- Enforces unique category names (via entity constraint)
- Provides reference data for ad classification

### StoreService

Manages dealership/store operations and ownership.

**Key Methods:**
- `createStore(Store)` - Creates a new store with default status
- `getStoreById(Long)` - Retrieves store by ID
- `getAllStores()` - Retrieves all stores
- `getStoresByOwner(UUID)` - Finds stores by owner
- `getStoreBySlug(String)` - Finds store by unique slug
- `updateStore(Long, Store)` - Updates existing store
- `updateStoreStatus(Long, String)` - Changes store status
- `deleteStore(Long)` - Deletes a store
- `storeExists(Long)` - Checks if store exists

**Business Rules:**
- Sets default status to "PENDING" for new stores
- Preserves owner ID during updates
- Enforces unique store names and slugs
- Manages store lifecycle (PENDING → ACTIVE → SUSPENDED → CLOSED)

### VehicleAdLikeService

Manages user likes/favorites on vehicle ads.

**Key Methods:**
- `likeAd(UUID, Long)` - Adds a like (prevents duplicates)
- `unlikeAd(UUID, Long)` - Removes a like
- `findLike(UUID, Long)` - Finds specific like
- `hasUserLikedAd(UUID, Long)` - Checks if user liked an ad
- `getLikesForAd(Long)` - Gets all likes for an ad
- `getLikeCount(Long)` - Gets total like count
- `getUserLikedAds(UUID)` - Gets all ads liked by user

**Business Rules:**
- Prevents duplicate likes from same user
- Allows users to unlike ads
- Provides like counting functionality
- Tracks user preferences

### VehicleMediaService

Manages media files (images, videos) for vehicle advertisements.

**Key Methods:**
- `addMedia(VehicleMedia)` - Adds media with auto-sort ordering
- `getMediaById(Long)` - Retrieves media by ID
- `getMediaForAd(Long)` - Gets all media for an ad (sorted)
- `getMediaByType(Long, String)` - Filters by media type (IMAGE, VIDEO)
- `getMediaByView(Long, String)` - Filters by view (EXTERIOR, INTERIOR, DASHBOARD)
- `updateMedia(Long, VehicleMedia)` - Updates media details
- `updateSortOrder(Long, Integer)` - Changes display order
- `deleteMedia(Long)` - Deletes single media
- `deleteAllMediaForAd(Long)` - Deletes all media for an ad

**Business Rules:**
- Auto-assigns sort order if not provided (appends to end)
- Returns media sorted by sortOrder
- Supports multiple media types and views
- Manages media lifecycle with parent ad

### StoreMediaService

Manages media files for stores (logos, banners, photos).

**Key Methods:**
- `addMedia(StoreMedia)` - Adds media with auto-sort ordering
- `getMediaById(Long)` - Retrieves media by ID
- `getMediaForStore(Long)` - Gets all media for a store (sorted)
- `getMediaByType(Long, String)` - Filters by type (IMAGE, VIDEO, LOGO, BANNER)
- `getMediaByView(Long, String)` - Filters by view (STOREFRONT, INTERIOR)
- `updateMedia(Long, StoreMedia)` - Updates media details
- `updateSortOrder(Long, Integer)` - Changes display order
- `deleteMedia(Long)` - Deletes single media
- `deleteAllMediaForStore(Long)` - Deletes all media for a store

**Business Rules:**
- Auto-assigns sort order if not provided
- Returns media sorted by sortOrder
- Supports store branding assets
- Manages media lifecycle with parent store

### StorePageConfigService

Manages store page customization and theme settings.

**Key Methods:**
- `createConfig(StorePageConfig)` - Creates new configuration
- `getConfigById(Long)` - Retrieves config by ID
- `getConfigByStoreId(Long)` - Retrieves config for specific store
- `getAllConfigs()` - Retrieves all configurations
- `updateConfig(Long, StorePageConfig)` - Updates full configuration
- `updateTheme(Long, String, String)` - Updates theme settings
- `updateColors(Long, String)` - Updates color scheme
- `updateLayout(Long, String)` - Updates layout settings
- `deleteConfig(Long)` - Deletes configuration
- `deleteConfigByStoreId(Long)` - Deletes config for store
- `configExistsForStore(Long)` - Checks if config exists

**Business Rules:**
- One configuration per store
- Stores customization as JSON strings
- Manages themes, colors, layouts, typography
- Supports store branding customization

## Common Patterns

### Transaction Management

All services use `@Transactional` annotation:
- Write operations use default transactional behavior
- Read operations use `@Transactional(readOnly = true)` for optimization

```java
@Transactional
public VehicleAd createAd(VehicleAd ad) {
    // Write operation - full transaction
}

@Transactional(readOnly = true)
public List<VehicleAd> getAllAds() {
    // Read operation - optimized
}
```

### Constructor Injection

Services use constructor injection for dependencies:
```java
public class VehicleAdService {
    private final VehicleAdRepository repository;
    
    public VehicleAdService(VehicleAdRepository repository) {
        this.repository = repository;
    }
}
```

### Error Handling

Services throw `RuntimeException` for not-found scenarios:
```java
.orElseThrow(() -> new RuntimeException("Entity not found with id: " + id))
```

*Note: Consider creating custom exceptions in production applications.*

### Validation

Services implement business validations:
- Default value assignment
- Duplicate prevention
- State validation
- Ownership verification

## Best Practices

1. **Keep Services Focused**: Each service manages one entity or related group
2. **Encapsulate Business Logic**: Don't put business rules in controllers
3. **Use Transactions**: Ensure data consistency with `@Transactional`
4. **Validate Input**: Check data before persistence
5. **Handle Errors Gracefully**: Use appropriate exception types
6. **Document Complex Logic**: Use JavaDoc for non-obvious operations
7. **Return Optionals**: Use `Optional<T>` for nullable results
8. **Prefer Read-Only Transactions**: Optimize queries with `readOnly = true`

## Testing Services

Service classes should be tested with unit tests:

```java
@ExtendWith(MockitoExtension.class)
class VehicleAdServiceTest {
    @Mock
    private VehicleAdRepository repository;
    
    @InjectMocks
    private VehicleAdService service;
    
    @Test
    void createAd_shouldSetDefaultStatus() {
        VehicleAd ad = new VehicleAd();
        when(repository.save(any())).thenReturn(ad);
        
        VehicleAd result = service.createAd(ad);
        
        assertEquals("DRAFT", result.getStatus());
    }
}
```

## Integration with Controllers

Controllers should delegate to services for all business operations:

```java
@RestController
@RequestMapping("/api/ads")
public class VehicleAdController {
    private final VehicleAdService vehicleAdService;
    
    // Controller handles HTTP concerns only
    // Service handles business logic
    
    @GetMapping("/{id}")
    public ResponseEntity<VehicleAd> getAd(@PathVariable Long id) {
        return vehicleAdService.getAdById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
```

## Summary

The service layer is the heart of the application's business logic:

- **Coordinates** data access through repositories
- **Implements** business rules and validations
- **Manages** transactions and data consistency
- **Transforms** data between layers
- **Protects** the domain model from external concerns

This separation ensures that business logic is testable, maintainable, and independent of HTTP/database implementation details.
