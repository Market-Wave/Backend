# DTO (Data Transfer Object) Layer Documentation

## Overview

The **DTO folder** contains Data Transfer Objects used for API request and response handling. DTOs provide a clear separation between the internal domain model (entities) and the external API contract, allowing for validation, transformation, and API versioning without affecting the database schema.

## Purpose

- **Decouple API from domain model**: Changes to entities don't break API contracts
- **Validation**: Apply input validation rules using Jakarta Bean Validation
- **Documentation**: Annotate DTOs with Swagger/OpenAPI metadata
- **Security**: Hide internal entity details and sensitive data
- **Flexibility**: Transform data structures for optimal API design

## DTO Structure

Each entity typically has:
- **Request DTO**: Used for POST/PUT operations (input)
- **Response DTO**: Used for GET operations (output)

### Naming Convention
- `EntityNameRequest` - For creating/updating resources
- `EntityNameResponse` - For returning resource data

## DTO Classes

### VehicleAdRequest & VehicleAdResponse

Main DTOs for vehicle advertisement operations.

**Request Fields:**
- Validation: `@NotNull`, `@NotBlank`, `@Min`, `@Max`, `@Size`, `@DecimalMin`
- Swagger examples for all fields
- Constrained values for enums (fuel type, transmission, condition, status)

**Response Fields:**
- Includes auto-generated `id` field
- All entity data without internal metadata

**Example Request:**
```json
{
  "ownerId": "550e8400-e29b-41d4-a716-446655440000",
  "storeId": 1,
  "title": "2020 Toyota Corolla XLE - Low Mileage",
  "description": "Well-maintained sedan with excellent fuel economy.",
  "brandId": 1,
  "modelId": 5,
  "bodyTypeId": 2,
  "categoryId": 1,
  "year": 2020,
  "mileage": 45000,
  "fuelType": "Gasoline",
  "transmission": "Automatic",
  "condition": "Excellent",
  "price": 25000.00,
  "currency": "USD",
  "country": "United States",
  "city": "Los Angeles",
  "latitude": 34.0522,
  "longitude": -118.2437,
  "address": "123 Main Street, Los Angeles, CA 90001",
  "status": "ACTIVE",
  "isBiddingEnabled": false,
  "isPreorderEnabled": false
}
```

### VehicleBrandRequest & VehicleBrandResponse

Simple DTOs for vehicle brand management.

**Fields:**
- `name` (required, unique)

**Example:**
```json
{
  "name": "Toyota"
}
```

### VehicleModelRequest & VehicleModelResponse

DTOs for vehicle model management with brand relationship.

**Request Fields:**
- `brandId` (required) - References parent brand
- `name` (required) - Model name

**Example:**
```json
{
  "brandId": 1,
  "name": "Corolla"
}
```

### BodyTypeRequest & BodyTypeResponse

Reference data DTOs for body types.

**Fields:**
- `name` (required, unique) - Body type name (Sedan, SUV, Hatchback, etc.)

### AdCategoryRequest & AdCategoryResponse

Reference data DTOs for advertisement categories.

**Fields:**
- `name` (required, unique) - Category name (Cars, Motorcycles, etc.)

### StoreRequest & StoreResponse

DTOs for dealership/store management.

**Request Fields:**
- `ownerId` (UUID, required)
- `name` (required, unique)
- `slug` (required, unique) - URL-friendly identifier
- `description` (max 1000 chars)
- Location fields: `country`, `city`, `latitude`, `longitude`, `address`
- `status` (PENDING, ACTIVE, SUSPENDED, CLOSED)

**Example:**
```json
{
  "ownerId": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Premium Auto Dealers",
  "slug": "premium-auto-dealers",
  "description": "Leading automotive dealership with 20+ years of experience.",
  "country": "United States",
  "city": "New York",
  "latitude": 40.7128,
  "longitude": -74.0060,
  "address": "456 Broadway, New York, NY 10013",
  "status": "ACTIVE"
}
```

### VehicleMediaRequest & VehicleMediaResponse

DTOs for managing vehicle advertisement media files.

**Request Fields:**
- `adId` (required) - Parent advertisement
- `url` (required) - Media file URL
- `mediaType` (required) - IMAGE, VIDEO
- `mediaView` (required) - EXTERIOR, INTERIOR, DASHBOARD, ENGINE, TRUNK, WHEELS
- `sortOrder` (optional) - Display order

**Example:**
```json
{
  "adId": 1,
  "url": "https://storage.example.com/vehicles/img001.jpg",
  "mediaType": "IMAGE",
  "mediaView": "EXTERIOR",
  "sortOrder": 0
}
```

### StoreMediaRequest & StoreMediaResponse

DTOs for managing store media files (logos, banners, photos).

**Request Fields:**
- `storeId` (required) - Parent store
- `url` (required) - Media file URL
- `mediaType` (required) - IMAGE, VIDEO, LOGO, BANNER
- `mediaView` (required) - STOREFRONT, INTERIOR, LOGO, BANNER, GALLERY
- `sortOrder` (optional) - Display order

### StorePageConfigRequest & StorePageConfigResponse

DTOs for store page customization and theming.

**Request Fields:**
- `storeId` (required)
- SEO: `metaTitle`, `metaDescription`
- Theme: `themeName`, `themeVersion`
- Customization JSON strings: `colors`, `buttons`, `typography`, `layout`, `pages`, `sections`

**Example:**
```json
{
  "storeId": 1,
  "metaTitle": "Premium Auto Dealers - Quality Vehicles",
  "metaDescription": "Browse our collection of quality vehicles.",
  "themeName": "modern-dark",
  "themeVersion": "1.0.0",
  "colors": "{\"primary\":\"#007bff\",\"secondary\":\"#6c757d\"}",
  "buttons": "{\"borderRadius\":\"4px\",\"padding\":\"10px 20px\"}",
  "typography": "{\"fontFamily\":\"Roboto, sans-serif\",\"fontSize\":\"16px\"}",
  "layout": "{\"header\":\"fixed\",\"sidebar\":\"left\"}",
  "pages": "{\"home\":true,\"about\":true,\"contact\":true}",
  "sections": "{\"hero\":true,\"featured\":true,\"testimonials\":false}"
}
```

## DtoMapper Utility

The `DtoMapper` class provides conversion methods between entities and DTOs.

**Methods:**
- `toEntity(Request)` - Converts request DTO to entity
- `toResponse(Entity)` - Converts entity to response DTO

**Usage in Controllers:**
```java
@PostMapping
public ResponseEntity<VehicleAdResponse> createAd(@Valid @RequestBody VehicleAdRequest request) {
    VehicleAd entity = dtoMapper.toEntity(request);
    VehicleAd created = vehicleAdService.createAd(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(created));
}
```

## Validation Annotations

DTOs use Jakarta Bean Validation annotations:

- `@NotNull` - Field cannot be null
- `@NotBlank` - String field cannot be null or empty
- `@Size(min, max)` - String/collection size constraints
- `@Min(value)` - Minimum numeric value
- `@Max(value)` - Maximum numeric value
- `@DecimalMin(value)` - Minimum decimal value
- `@Pattern(regexp)` - String pattern matching

**Validation is triggered automatically** when using `@Valid` in controller methods.

## Swagger/OpenAPI Annotations

DTOs use Swagger annotations for API documentation:

- `@Schema(description, example)` - Describes field with example value
- `description` - Human-readable field description
- `example` - Sample value shown in Swagger UI
- `allowableValues` - Enumerated valid values

**These annotations automatically generate:**
- Interactive API documentation
- Request/response examples
- Validation constraints display
- Try-it-out functionality

## Best Practices

1. **Keep DTOs Simple**: Only include fields needed for the API
2. **Use Validation**: Validate all inputs at the API boundary
3. **Provide Examples**: Add meaningful Swagger examples
4. **Separate Concerns**: Don't reuse DTOs across unrelated endpoints
5. **Version DTOs**: Create new DTOs for API version changes
6. **Document Constraints**: Use clear validation messages
7. **Avoid Business Logic**: DTOs are data containers only
8. **Match API Design**: Structure DTOs for optimal API usability

## Example DTO Usage Flow

```
1. Client sends JSON → Request DTO (validated)
2. Controller receives @Valid Request DTO
3. DtoMapper converts Request DTO → Entity
4. Service processes Entity
5. Service returns Entity
6. DtoMapper converts Entity → Response DTO
7. Controller returns Response DTO → JSON to client
```

## Benefits

- **Type Safety**: Compile-time checks for API contracts
- **Validation**: Automatic input validation before business logic
- **Documentation**: Self-documenting with Swagger annotations
- **Evolution**: Change DTOs without affecting domain model
- **Security**: Control what data is exposed via API
- **Testability**: Easy to create test data with DTOs

## Swagger UI Access

Once the application is running, access interactive API documentation at:

**URL**: `http://localhost:8080/swagger-ui.html`

The Swagger UI provides:
- All endpoints with descriptions
- Request/response schemas with examples
- Try-it-out functionality
- Response codes and error descriptions
- Model definitions
