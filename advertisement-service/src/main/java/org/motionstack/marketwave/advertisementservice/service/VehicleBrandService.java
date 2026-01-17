package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.VehicleBrand;
import org.motionstack.marketwave.advertisementservice.repository.VehicleBrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for VehicleBrand entity.
 * Manages vehicle brand data including CRUD operations and validation.
 */
@Service
@Transactional
public class VehicleBrandService {

    private final VehicleBrandRepository vehicleBrandRepository;

    public VehicleBrandService(VehicleBrandRepository vehicleBrandRepository) {
        this.vehicleBrandRepository = vehicleBrandRepository;
    }

    /**
     * Create a new vehicle brand.
     *
     * @param brand the vehicle brand to create
     * @return the saved vehicle brand
     */
    public VehicleBrand createBrand(VehicleBrand brand) {
        return vehicleBrandRepository.save(brand);
    }

    /**
     * Retrieve a vehicle brand by ID.
     *
     * @param id the brand ID
     * @return Optional containing the brand if found
     */
    @Transactional(readOnly = true)
    public Optional<VehicleBrand> getBrandById(Long id) {
        return vehicleBrandRepository.findById(id);
    }

    /**
     * Retrieve all vehicle brands.
     *
     * @return list of all brands
     */
    @Transactional(readOnly = true)
    public List<VehicleBrand> getAllBrands() {
        return vehicleBrandRepository.findAll();
    }

    /**
     * Update an existing vehicle brand.
     *
     * @param id the brand ID
     * @param updatedBrand the updated brand data
     * @return the updated brand
     */
    public VehicleBrand updateBrand(Long id, VehicleBrand updatedBrand) {
        return vehicleBrandRepository.findById(id)
                .map(brand -> {
                    updatedBrand.setId(id);
                    return vehicleBrandRepository.save(updatedBrand);
                })
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    /**
     * Delete a vehicle brand.
     *
     * @param id the brand ID to delete
     */
    public void deleteBrand(Long id) {
        vehicleBrandRepository.deleteById(id);
    }

    /**
     * Check if a brand exists.
     *
     * @param id the brand ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean brandExists(Long id) {
        return vehicleBrandRepository.existsById(id);
    }
}
