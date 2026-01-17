package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.VehicleAd;
import org.motionstack.marketwave.advertisementservice.repository.VehicleAdRepository;
import org.motionstack.marketwave.advertisementservice.repository.VehicleBrandRepository;
import org.motionstack.marketwave.advertisementservice.repository.VehicleModelRepository;
import org.motionstack.marketwave.advertisementservice.repository.BodyTypeRepository;
import org.motionstack.marketwave.advertisementservice.repository.AdCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for VehicleAd entity.
 * Handles business logic for vehicle advertisements including CRUD operations,
 * validation, and status management.
 */
@Service
@Transactional
public class VehicleAdService {

    private final VehicleAdRepository vehicleAdRepository;
    private final VehicleBrandRepository vehicleBrandRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final AdCategoryRepository adCategoryRepository;

    public VehicleAdService(VehicleAdRepository vehicleAdRepository,
                           VehicleBrandRepository vehicleBrandRepository,
                           VehicleModelRepository vehicleModelRepository,
                           BodyTypeRepository bodyTypeRepository,
                           AdCategoryRepository adCategoryRepository) {
        this.vehicleAdRepository = vehicleAdRepository;
        this.vehicleBrandRepository = vehicleBrandRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.bodyTypeRepository = bodyTypeRepository;
        this.adCategoryRepository = adCategoryRepository;
    }

    /**
     * Create a new vehicle advertisement.
     *
     * @param vehicleAd the vehicle ad to create
     * @return the saved vehicle ad with generated ID
     */
    public VehicleAd createAd(VehicleAd vehicleAd) {
        // Set default status if not provided
        if (vehicleAd.getStatus() == null) {
            vehicleAd.setStatus("DRAFT");
        }
        return vehicleAdRepository.save(vehicleAd);
    }

    /**
     * Retrieve a vehicle ad by its ID.
     *
     * @param id the vehicle ad ID
     * @return Optional containing the vehicle ad if found
     */
    @Transactional(readOnly = true)
    public Optional<VehicleAd> getAdById(Long id) {
        return vehicleAdRepository.findById(id);
    }

    /**
     * Retrieve all vehicle advertisements.
     *
     * @return list of all vehicle ads
     */
    @Transactional(readOnly = true)
    public List<VehicleAd> getAllAds() {
        return vehicleAdRepository.findAll();
    }

    /**
     * Update an existing vehicle advertisement.
     *
     * @param id the vehicle ad ID
     * @param updatedAd the updated vehicle ad data
     * @return the updated vehicle ad
     * @throws RuntimeException if ad not found
     */
    public VehicleAd updateAd(Long id, VehicleAd updatedAd) {
        return vehicleAdRepository.findById(id)
                .map(existingAd -> {
                    updatedAd.setId(id);
                    // Preserve owner if not provided in update
                    if (updatedAd.getOwnerId() == null) {
                        updatedAd.setOwnerId(existingAd.getOwnerId());
                    }
                    return vehicleAdRepository.save(updatedAd);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle ad not found with id: " + id));
    }

    /**
     * Delete a vehicle advertisement.
     *
     * @param id the vehicle ad ID to delete
     */
    public void deleteAd(Long id) {
        vehicleAdRepository.deleteById(id);
    }

    /**
     * Find all ads by owner ID.
     *
     * @param ownerId the owner's UUID
     * @return list of vehicle ads belonging to the owner
     */
    @Transactional(readOnly = true)
    public List<VehicleAd> getAdsByOwner(UUID ownerId) {
        return vehicleAdRepository.findAll().stream()
                .filter(ad -> ad.getOwnerId().equals(ownerId))
                .toList();
    }

    /**
     * Find all ads by store ID.
     *
     * @param storeId the store's ID
     * @return list of vehicle ads belonging to the store
     */
    @Transactional(readOnly = true)
    public List<VehicleAd> getAdsByStore(Long storeId) {
        return vehicleAdRepository.findAll().stream()
                .filter(ad -> ad.getStoreId() != null && ad.getStoreId().equals(storeId))
                .toList();
    }

    /**
     * Update the status of a vehicle ad.
     *
     * @param id the vehicle ad ID
     * @param status the new status (DRAFT, ACTIVE, INACTIVE, SOLD, etc.)
     * @return the updated vehicle ad
     */
    public VehicleAd updateAdStatus(Long id, String status) {
        return vehicleAdRepository.findById(id)
                .map(ad -> {
                    ad.setStatus(status);
                    return vehicleAdRepository.save(ad);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle ad not found with id: " + id));
    }

    /**
     * Check if a vehicle ad exists.
     *
     * @param id the vehicle ad ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean adExists(Long id) {
        return vehicleAdRepository.existsById(id);
    }
}
