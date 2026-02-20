package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.VehicleModel;
import org.motionstack.marketwave.advertisementservice.repository.VehicleModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for VehicleModel entity.
 * Manages vehicle models and their relationships with brands.
 */
@Service
@Transactional
public class VehicleModelService {

    private final VehicleModelRepository vehicleModelRepository;

    public VehicleModelService(VehicleModelRepository vehicleModelRepository) {
        this.vehicleModelRepository = vehicleModelRepository;
    }

    /**
     * Create a new vehicle model.
     *
     * @param model the vehicle model to create
     * @return the saved vehicle model
     */
    public VehicleModel createModel(VehicleModel model) {
        return vehicleModelRepository.save(model);
    }

    /**
     * Retrieve a vehicle model by ID.
     *
     * @param id the model ID
     * @return Optional containing the model if found
     */
    @Transactional(readOnly = true)
    public Optional<VehicleModel> getModelById(Long id) {
        return vehicleModelRepository.findById(id);
    }

    /**
     * Retrieve all vehicle models.
     *
     * @return list of all models
     */
    @Transactional(readOnly = true)
    public List<VehicleModel> getAllModels() {
        return vehicleModelRepository.findAll();
    }

    /**
     * Retrieve all models for a specific brand.
     *
     * @param brandId the brand ID
     * @return list of models belonging to the brand
     */
    @Transactional(readOnly = true)
    public List<VehicleModel> getModelsByBrand(Long brandId) {
        return vehicleModelRepository.findAll().stream()
                .filter(model -> model.getBrandId().equals(brandId))
                .toList();
    }

    /**
     * Update an existing vehicle model.
     *
     * @param id the model ID
     * @param updatedModel the updated model data
     * @return the updated model
     */
    public VehicleModel updateModel(Long id, VehicleModel updatedModel) {
        return vehicleModelRepository.findById(id)
                .map(model -> {
                    model.setName(updatedModel.getName());
                    model.setBrandId(updatedModel.getBrandId());
                    return vehicleModelRepository.save(model);
                })
                .orElseThrow(() -> new RuntimeException("Model not found with id: " + id));
    }

    /**
     * Delete a vehicle model.
     *
     * @param id the model ID to delete
     */
    public void deleteModel(Long id) {
        if (!vehicleModelRepository.existsById(id)) {
            throw new RuntimeException("Model not found with id: " + id);
        }
        vehicleModelRepository.deleteById(id);
    }

    /**
     * Check if a model exists.
     *
     * @param id the model ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean modelExists(Long id) {
        return vehicleModelRepository.existsById(id);
    }
}
