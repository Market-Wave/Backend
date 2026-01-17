# Repository Layer Documentation

This folder contains the Spring Data JPA repositories used by the Advertisement Service.
Repositories act as the data-access layer, providing an abstraction over the database so that services and controllers can work with entities using simple Java methods instead of raw SQL.

## Folder Purpose

- Encapsulate all persistence-related interfaces for the advertisement domain.
- Provide CRUD (Create, Read, Update, Delete) operations for entities such as `VehicleAd`.
- Expose additional query methods (e.g., find by brand, price range, etc.) using Spring Data JPA's method naming conventions.
- Keep database access logic separated from business logic (services) and HTTP logic (controllers).

Typical call flow in the application:

`Controller → Service → Repository → Database`

## Key Concepts

- **Repository interface**: A Java interface that extends a Spring Data base interface like `JpaRepository`.
- **Entity type**: The JPA entity managed by the repository (e.g., `VehicleAd`).
- **ID type**: The Java type of the entity's primary key (e.g., `Long`).
- **Spring-generated implementation**: Spring automatically creates a class at runtime for each repository interface, so no manual implementation is required.

## VehicleAdRepository

Example repository in this folder:

```java
package org.motionstack.marketwave.advertisementservice.repository;

import org.motionstack.marketwave.advertisementservice.model.VehicleAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAdRepository extends JpaRepository<VehicleAd, Long> {
}
```

### What It Does

Manages the `VehicleAd` entity.

Uses `Long` as the ID type for `VehicleAd`.

Inherits a full set of common methods from `JpaRepository`, such as:

- `save(VehicleAd entity)` – Insert or update a vehicle advertisement.
- `findById(Long id)` – Retrieve a single advertisement by its ID.
- `findAll()` – Retrieve all advertisements.
- `deleteById(Long id)` – Delete an advertisement by its ID.
- Pagination and sorting support via methods like `findAll(Pageable pageable)`.

### Role of @Repository

Marks `VehicleAdRepository` as a Spring bean so it is discovered by component scanning.

Allows it to be injected into services via constructor injection.

Enables exception translation from low-level persistence exceptions to Spring's data access exceptions.

### Typical Usage From a Service

```java
@Service
public class VehicleAdService {

    private final VehicleAdRepository vehicleAdRepository;

    public VehicleAdService(VehicleAdRepository vehicleAdRepository) {
        this.vehicleAdRepository = vehicleAdRepository;
    }

    public VehicleAd createAd(VehicleAd ad) {
        return vehicleAdRepository.save(ad);
    }

    public Optional<VehicleAd> getAd(Long id) {
        return vehicleAdRepository.findById(id);
    }

    public List<VehicleAd> getAllAds() {
        return vehicleAdRepository.findAll();
    }

    public void deleteAd(Long id) {
        vehicleAdRepository.deleteById(id);
    }
}
```

The service focuses on business logic, while the repository focuses on data persistence.

## Adding Custom Query Methods

You can extend `VehicleAdRepository` with additional methods by following Spring Data JPA's method naming conventions:

```java
@Repository
public interface VehicleAdRepository extends JpaRepository<VehicleAd, Long> {

    List<VehicleAd> findByBrand(String brand);

    List<VehicleAd> findByPriceLessThanEqual(BigDecimal maxPrice);

    List<VehicleAd> findByBrandAndModel(String brand, String model);

    List<VehicleAd> findByYearBetween(Integer startYear, Integer endYear);
}
```

Spring automatically generates the underlying queries based on these method names, as long as the names match the fields of the `VehicleAd` entity.

## Other Repositories in This Folder

If this folder contains more repository interfaces (for example, `UserRepository`, `ImageRepository`, `LocationRepository`, etc.), they typically follow the same pattern:

```java
@Repository
public interface SomeEntityRepository extends JpaRepository<SomeEntity, IdType> {
    // Optional: custom query methods
}
```

Each repository:

- Targets a specific entity.
- Defines the ID type.
- May declare additional finder methods to support the domain's query requirements.

To fully document them, replicate the structure used above for `VehicleAdRepository`:

- Brief description of the entity it manages.
- The purpose of the repository.
- Important methods (custom queries) and what they return.