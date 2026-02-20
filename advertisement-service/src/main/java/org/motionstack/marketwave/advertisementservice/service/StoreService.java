package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.Store;
import org.motionstack.marketwave.advertisementservice.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for Store entity.
 * Manages dealership/store data including CRUD operations and ownership management.
 */
@Service
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Create a new store.
     *
     * @param store the store to create
     * @return the saved store
     */
    public Store createStore(Store store) {
        // Set default status if not provided
        if (store.getStatus() == null) {
            store.setStatus("PENDING");
        }
        return storeRepository.save(store);
    }

    /**
     * Retrieve a store by ID.
     *
     * @param id the store ID
     * @return Optional containing the store if found
     */
    @Transactional(readOnly = true)
    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    /**
     * Retrieve all stores.
     *
     * @return list of all stores
     */
    @Transactional(readOnly = true)
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    /**
     * Find stores by owner ID.
     *
     * @param ownerId the owner's UUID
     * @return list of stores owned by the user
     */
    @Transactional(readOnly = true)
    public List<Store> getStoresByOwner(UUID ownerId) {
        return storeRepository.findAll().stream()
                .filter(store -> store.getOwnerId().equals(ownerId))
                .toList();
    }

    /**
     * Find a store by slug.
     *
     * @param slug the store's unique slug
     * @return Optional containing the store if found
     */
    @Transactional(readOnly = true)
    public Optional<Store> getStoreBySlug(String slug) {
        return storeRepository.findAll().stream()
                .filter(store -> store.getSlug().equals(slug))
                .findFirst();
    }

    /**
     * Update an existing store.
     *
     * @param id the store ID
     * @param updatedStore the updated store data
     * @return the updated store
     */
    public Store updateStore(Long id, Store updatedStore) {
        return storeRepository.findById(id)
                .map(store -> {
                    if (updatedStore.getName() != null) {
                        store.setName(updatedStore.getName());
                    }
                    if (updatedStore.getSlug() != null) {
                        store.setSlug(updatedStore.getSlug());
                    }
                    if (updatedStore.getDescription() != null) {
                        store.setDescription(updatedStore.getDescription());
                    }
                    if (updatedStore.getCountry() != null) {
                        store.setCountry(updatedStore.getCountry());
                    }
                    if (updatedStore.getCity() != null) {
                        store.setCity(updatedStore.getCity());
                    }
                    if (updatedStore.getLatitude() != null) {
                        store.setLatitude(updatedStore.getLatitude());
                    }
                    if (updatedStore.getLongitude() != null) {
                        store.setLongitude(updatedStore.getLongitude());
                    }
                    if (updatedStore.getAddress() != null) {
                        store.setAddress(updatedStore.getAddress());
                    }
                    if (updatedStore.getStatus() != null) {
                        store.setStatus(updatedStore.getStatus());
                    }
                    return storeRepository.save(store);
                })
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    /**
     * Update the status of a store.
     *
     * @param id the store ID
     * @param status the new status (PENDING, ACTIVE, SUSPENDED, CLOSED)
     * @return the updated store
     */
    public Store updateStoreStatus(Long id, String status) {
        return storeRepository.findById(id)
                .map(store -> {
                    store.setStatus(status);
                    return storeRepository.save(store);
                })
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    /**
     * Delete a store.
     *
     * @param id the store ID to delete
     */
    public void deleteStore(Long id) {
        if (!storeRepository.existsById(id)) {
            throw new RuntimeException("Store not found with id: " + id);
        }
        storeRepository.deleteById(id);
    }

    /**
     * Check if a store exists.
     *
     * @param id the store ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean storeExists(Long id) {
        return storeRepository.existsById(id);
    }

    /**
     * Check if a store name already exists.
     *
     * @param name the store name
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean storeNameExists(String name) {
        return storeRepository.existsByName(name);
    }

    /**
     * Check if a store slug already exists.
     *
     * @param slug the store slug
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean storeSlugExists(String slug) {
        return storeRepository.existsBySlug(slug);
    }
}
