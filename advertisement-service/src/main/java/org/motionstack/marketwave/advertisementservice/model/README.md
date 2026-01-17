# Model Layer Documentation

## Overview

The **model folder** contains JPA entity classes that represent the database tables for the Advertisement Service. Each class defines the structure, relationships, and constraints for the corresponding database table.

## Purpose

- Define database schema through Java classes
- Provide type-safe data models for the application
- Enable JPA/Hibernate to handle database operations automatically
- Maintain data integrity through annotations and constraints

## Entity Classes

### VehicleAd
The main entity representing vehicle advertisements.

```java
@Entity
@Table(name = "vehicle_ads")
public class VehicleAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID ownerId;

    private Long storeId;
    private String title;
    private String description;
    private Long brandId;
    private Long modelId;
    private Long bodyTypeId;
    private Long categoryId;
    private Integer year;
    private Integer mileage;
    private String fuelType;
    private String transmission;
    private String condition;
    private BigDecimal price;
    private String currency;
    private String country;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String status;
    private Boolean isBiddingEnabled;
    private Boolean isPreorderEnabled;
}
```

**Key Fields:**
- `id`: Primary key (auto-generated)
- `ownerId`: UUID of the ad owner
- `storeId`: Reference to associated store
- `title`: Ad title (max 500 chars)
- `price`: Vehicle price (decimal 10,2)
- `status`: Ad status (ACTIVE, INACTIVE, etc.)

### VehicleBrand
Represents vehicle manufacturers/brands.

```java
@Entity
@Table(name = "vehicle_brands")
public class VehicleBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
```

### VehicleModel
Represents specific vehicle models within brands.

```java
@Entity
@Table(name = "vehicle_models")
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private String name;
}
```

### BodyType
Represents vehicle body types (sedan, SUV, hatchback, etc.).

```java
@Entity
@Table(name = "body_types")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
```

### AdCategory
Represents advertisement categories.

```java
@Entity
@Table(name = "ad_categories")
public class AdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
```

### Store
Represents dealerships or stores that can post advertisements.

```java
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @Column(length = 500)
    private String address;

    @Column(nullable = false)
    private String status;
}
```

### VehicleAdLike
Represents user likes on vehicle advertisements.

```java
@Entity
@Table(name = "vehicle_ad_likes")
public class VehicleAdLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Long adId;
}
```

### VehicleMedia
Represents media files (images, videos) associated with vehicle ads.

```java
@Entity
@Table(name = "vehicle_media")
public class VehicleMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long adId;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(nullable = false)
    private String mediaType;

    @Column(nullable = false)
    private String mediaView;

    @Column(nullable = false)
    private Integer sortOrder;
}
```

### StoreMedia
Represents media files associated with stores.

```java
@Entity
@Table(name = "store_media")
public class StoreMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(nullable = false)
    private String mediaType;

    @Column(nullable = false)
    private String mediaView;

    @Column(nullable = false)
    private Integer sortOrder;
}
```

### StorePageConfig
Represents configuration settings for store pages (themes, layouts, etc.).

```java
@Entity
@Table(name = "store_page_configs")
public class StorePageConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", insertable = false, updatable = false)
    private Store store;

    @Column(length = 200)
    private String metaTitle;

    @Column(length = 500)
    private String metaDescription;

    @Column(length = 100)
    private String themeName;

    @Column(length = 50)
    private String themeVersion;

    @Column(length = 1000)
    private String colors;

    @Column(length = 500)
    private String buttons;

    @Column(length = 1000)
    private String typography;

    @Column(length = 500)
    private String layout;

    @Column(length = 1000)
    private String pages;

    @Column(length = 1000)
    private String sections;
}
```

## Common Annotations

### Class-Level Annotations
- `@Entity`: Marks class as JPA entity
- `@Table(name = "...")`: Specifies table name
- `@Data`: Lombok annotation for getters/setters/toString/equals/hashCode
- `@NoArgsConstructor` / `@AllArgsConstructor`: Lombok constructors

### Field-Level Annotations
- `@Id`: Primary key
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Auto-increment ID
- `@Column`: Column mapping with constraints (nullable, length, precision, scale)
- `@ManyToOne`: JPA relationship annotation (used in StorePageConfig)

## Relationships

- `VehicleAd` references `VehicleBrand`, `VehicleModel`, `BodyType`, `AdCategory`, `Store`
- `VehicleModel` references `VehicleBrand`
- `StorePageConfig` has a many-to-one relationship with `Store`
- `VehicleAdLike` references `VehicleAd` (via adId)
- `VehicleMedia` references `VehicleAd` (via adId)
- `StoreMedia` references `Store` (via storeId)

## Data Flow

```
Controller → Service → Repository → Entity → Database
```

Entities are used throughout the application layers to maintain type safety and enable JPA's automatic CRUD operations.