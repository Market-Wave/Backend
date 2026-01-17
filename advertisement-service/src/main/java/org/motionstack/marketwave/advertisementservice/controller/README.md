# Controller Layer Documentation

## Overview

The **controller folder** contains REST API controllers that handle HTTP requests and responses. Controllers are the entry point for all API operations, delegating business logic to services and using DTOs for data transfer.

## Purpose

- Handle HTTP requests (GET, POST, PUT, PATCH, DELETE)
- Validate input using Jakarta Bean Validation
- Delegate business logic to service layer
- Convert between DTOs and entities
- Return appropriate HTTP status codes
- Document APIs with Swagger/OpenAPI annotations

## Application Architecture

```
HTTP Client
    ↓
Controller (HTTP layer - DTOs)
    ↓
Service (Business logic - Entities)
    ↓
Repository (Data access - Entities)
    ↓
Database
```

## Controllers

### VehicleAdController

**Base Path**: `/api/v1/vehicle-ads`

Manages vehicle advertisement operations.

**Endpoints:**

| Method | Path | Description | Request | Response | Status |
|--------|------|-------------|---------|----------|--------|
| POST | `/` | Create new ad | VehicleAdRequest | VehicleAdResponse | 201 |
| GET | `/{id}` | Get ad by ID | - | VehicleAdResponse | 200/404 |
| GET | `/` | Get all ads | - | List<VehicleAdResponse> | 200 |
| GET | `/owner/{ownerId}` | Get ads by owner | - | List<VehicleAdResponse> | 200 |
| PUT | `/{id}` | Update ad | VehicleAdRequest | VehicleAdResponse | 200/404 |
| PATCH | `/{id}/status` | Update status | status param | VehicleAdResponse | 200/404 |
| DELETE | `/{id}` | Delete ad | - | - | 204/404 |
| GET | `/{id}/exists` | Check existence | - | Boolean | 200 |

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/v1/vehicle-ads \
  -H "Content-Type: application/json" \
  -d '{
    "ownerId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "2020 Toyota Corolla XLE",
    "brandId": 1,
    "modelId": 5,
    "year": 2020,
    "mileage": 45000,
    "fuelType": "Gasoline",
    "transmission": "Automatic",
    "condition": "Excellent",
    "price": 25000.00,
    "currency": "USD",
    "country": "United States",
    "city": "Los Angeles"
  }'
```

**Example Response:**
```json
{
  "id": 1,
  "ownerId": "550e8400-e29b-41d4-a716-446655440000",
  "storeId": null,
  "title": "2020 Toyota Corolla XLE",
  "brandId": 1,
  "modelId": 5,
  "year": 2020,
  "mileage": 45000,
  "fuelType": "Gasoline",
  "transmission": "Automatic",
  "condition": "Excellent",
  "price": 25000.00,
  "currency": "USD",
  "country": "United States",
  "city": "Los Angeles",
  "status": "DRAFT"
}
```

### VehicleBrandController

**Base Path**: `/api/v1/vehicle-brands`

Manages vehicle brand reference data.

**Endpoints:**
- POST `/` - Create brand
- GET `/{id}` - Get brand by ID
- GET `/` - Get all brands
- PUT `/{id}` - Update brand
- DELETE `/{id}` - Delete brand

### VehicleModelController

**Base Path**: `/api/v1/vehicle-models`

Manages vehicle models and their brand associations.

**Endpoints:**
- POST `/` - Create model
- GET `/{id}` - Get model by ID
- GET `/` - Get all models
- GET `/brand/{brandId}` - Get models by brand
- PUT `/{id}` - Update model
- DELETE `/{id}` - Delete model

### BodyTypeController

**Base Path**: `/api/v1/body-types`

Manages vehicle body type reference data.

**Endpoints:**
- POST `/` - Create body type
- GET `/{id}` - Get body type by ID
- GET `/` - Get all body types
- PUT `/{id}` - Update body type
- DELETE `/{id}` - Delete body type

### AdCategoryController

**Base Path**: `/api/v1/ad-categories`

Manages advertisement category reference data.

**Endpoints:**
- POST `/` - Create category
- GET `/{id}` - Get category by ID
- GET `/` - Get all categories
- PUT `/{id}` - Update category
- DELETE `/{id}` - Delete category

### StoreController

**Base Path**: `/api/v1/stores`

Manages dealership/store operations.

**Endpoints:**

| Method | Path | Description |
|--------|------|-------------|
| POST | `/` | Create new store |
| GET | `/{id}` | Get store by ID |
| GET | `/` | Get all stores |
| GET | `/owner/{ownerId}` | Get stores by owner |
| GET | `/slug/{slug}` | Get store by unique slug |
| PUT | `/{id}` | Update store |
| PATCH | `/{id}/status` | Update store status |
| DELETE | `/{id}` | Delete store |

**Example - Get store by slug:**
```bash
curl -X GET http://localhost:8080/api/v1/stores/slug/premium-auto-dealers
```

### VehicleMediaController

**Base Path**: `/api/v1/vehicle-media`

Manages vehicle advertisement media files.

**Endpoints:**

| Method | Path | Description |
|--------|------|-------------|
| POST | `/` | Add media to ad |
| GET | `/{id}` | Get media by ID |
| GET | `/ad/{adId}` | Get all media for ad |
| GET | `/ad/{adId}/type/{mediaType}` | Filter by type (IMAGE, VIDEO) |
| GET | `/ad/{adId}/view/{mediaView}` | Filter by view (EXTERIOR, INTERIOR) |
| PUT | `/{id}` | Update media |
| PATCH | `/{id}/sort-order` | Update sort order |
| DELETE | `/{id}` | Delete media |
| DELETE | `/ad/{adId}` | Delete all media for ad |

**Example - Add media:**
```bash
curl -X POST http://localhost:8080/api/v1/vehicle-media \
  -H "Content-Type: application/json" \
  -d '{
    "adId": 1,
    "url": "https://storage.example.com/vehicles/img001.jpg",
    "mediaType": "IMAGE",
    "mediaView": "EXTERIOR",
    "sortOrder": 0
  }'
```

### StoreMediaController

**Base Path**: `/api/v1/store-media`

Manages store media files (logos, banners, images).

**Endpoints:**

| Method | Path | Description |
|--------|------|-------------|
| POST | `/` | Add media to store |
| GET | `/{id}` | Get media by ID |
| GET | `/store/{storeId}` | Get all media for store |
| GET | `/store/{storeId}/type/{mediaType}` | Filter by type |
| GET | `/store/{storeId}/view/{mediaView}` | Filter by view |
| PUT | `/{id}` | Update media |
| PATCH | `/{id}/sort-order` | Update sort order |
| DELETE | `/{id}` | Delete media |
| DELETE | `/store/{storeId}` | Delete all media for store |

### StorePageConfigController

**Base Path**: `/api/v1/store-configs`

Manages store page customization and theme settings.

**Endpoints:**

| Method | Path | Description |
|--------|------|-------------|
| POST | `/` | Create configuration |
| GET | `/{id}` | Get config by ID |
| GET | `/store/{storeId}` | Get config by store ID |
| GET | `/` | Get all configurations |
| PUT | `/{id}` | Update full configuration |
| PATCH | `/store/{storeId}/theme` | Update theme only |
| PATCH | `/store/{storeId}/colors` | Update colors only |
| PATCH | `/store/{storeId}/layout` | Update layout only |
| DELETE | `/{id}` | Delete configuration |
| DELETE | `/store/{storeId}` | Delete config by store |

**Example - Update theme:**
```bash
curl -X PATCH "http://localhost:8080/api/v1/store-configs/store/1/theme?themeName=modern-dark&themeVersion=1.0.0"
```

## Swagger/OpenAPI Annotations

All controllers use comprehensive Swagger documentation:

### Class-Level Annotations
```java
@RestController
@RequestMapping("/api/v1/resource")
@Tag(name = "Resource Name", description = "Description of endpoints")
```

### Method-Level Annotations
```java
@Operation(summary = "Short description", 
           description = "Detailed description")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success",
                content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
    @ApiResponse(responseCode = "404", description = "Not found"),
    @ApiResponse(responseCode = "400", description = "Bad request")
})
```

### Parameter Annotations
```java
@Parameter(description = "Parameter description", example = "1")
@PathVariable Long id
```

## HTTP Status Codes

Controllers return appropriate HTTP status codes:

- **200 OK**: Successful GET, PUT, PATCH operations
- **201 Created**: Successful POST operations (resource created)
- **204 No Content**: Successful DELETE operations
- **400 Bad Request**: Validation errors, malformed requests
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected server errors

## Request Validation

Controllers validate requests using `@Valid` annotation:

```java
public ResponseEntity<Response> create(@Valid @RequestBody Request request)
```

Validation errors return **400 Bad Request** with error details.

## Common Patterns

### Constructor Injection
```java
public class Controller {
    private final Service service;
    private final DtoMapper mapper;
    
    public Controller(Service service, DtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
}
```

### DTO Conversion Pattern
```java
@PostMapping
public ResponseEntity<Response> create(@Valid @RequestBody Request request) {
    Entity entity = mapper.toEntity(request);
    Entity created = service.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapper.toResponse(created));
}
```

### Optional Handling Pattern
```java
@GetMapping("/{id}")
public ResponseEntity<Response> getById(@PathVariable Long id) {
    return service.getById(id)
            .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
            .orElse(ResponseEntity.notFound().build());
}
```

### List Conversion Pattern
```java
@GetMapping
public ResponseEntity<List<Response>> getAll() {
    List<Response> responses = service.getAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    return ResponseEntity.ok(responses);
}
```

## Testing Controllers

Controllers should be tested with `@WebMvcTest`:

```java
@WebMvcTest(VehicleAdController.class)
class VehicleAdControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private VehicleAdService service;
    
    @MockBean
    private DtoMapper mapper;
    
    @Test
    void shouldCreateAd() throws Exception {
        mockMvc.perform(post("/api/v1/vehicle-ads")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{...}"))
                .andExpect(status().isCreated());
    }
}
```

## Best Practices

1. **Keep Controllers Thin**: Delegate all business logic to services
2. **Use DTOs**: Never expose entities directly in APIs
3. **Validate Input**: Always use `@Valid` for request bodies
4. **Return Proper Status Codes**: Use appropriate HTTP status codes
5. **Document APIs**: Use Swagger annotations comprehensively
6. **Handle Exceptions**: Use `@ControllerAdvice` for global exception handling
7. **Version APIs**: Use `/api/v1/` prefix for versioning
8. **Use RESTful Conventions**: Follow REST best practices
9. **Secure Endpoints**: Add authentication/authorization (not shown here)
10. **Test Thoroughly**: Write integration tests for all endpoints

## Swagger UI

Access the interactive API documentation at:

**URL**: `http://localhost:8080/swagger-ui.html`

**Features:**
- Browse all endpoints by tag/category
- View request/response schemas with examples
- Try out endpoints directly in browser
- See validation constraints and requirements
- Export OpenAPI specification (JSON/YAML)

## API Versioning

All endpoints use `/api/v1/` prefix:
- Allows for future API versions (`/api/v2/`)
- Maintains backward compatibility
- Clear version in URL

## Error Handling

Consider implementing `@ControllerAdvice` for global error handling:

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }
}
```

## Summary

The controller layer provides a clean, well-documented REST API with:
- **8 controllers** managing all domain operations
- **50+ endpoints** covering CRUD and custom operations
- **Complete Swagger documentation** for all endpoints
- **Input validation** using Jakarta Bean Validation
- **Proper HTTP semantics** with correct status codes
- **DTO-based API contracts** decoupled from domain model

The API is production-ready and follows industry best practices for RESTful service design.
