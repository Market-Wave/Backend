package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.BodyType;
import org.motionstack.marketwave.advertisementservice.repository.BodyTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for BodyType entity.
 * Manages vehicle body types (sedan, SUV, hatchback, etc.).
 */
@Service
@Transactional
public class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypeService(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    /**
     * Create a new body type.
     *
     * @param bodyType the body type to create
     * @return the saved body type
     */
    public BodyType createBodyType(BodyType bodyType) {
        return bodyTypeRepository.save(bodyType);
    }

    /**
     * Retrieve a body type by ID.
     *
     * @param id the body type ID
     * @return Optional containing the body type if found
     */
    @Transactional(readOnly = true)
    public Optional<BodyType> getBodyTypeById(Long id) {
        return bodyTypeRepository.findById(id);
    }

    /**
     * Retrieve all body types.
     *
     * @return list of all body types
     */
    @Transactional(readOnly = true)
    public List<BodyType> getAllBodyTypes() {
        return bodyTypeRepository.findAll();
    }

    /**
     * Update an existing body type.
     *
     * @param id the body type ID
     * @param updatedBodyType the updated body type data
     * @return the updated body type
     */
    public BodyType updateBodyType(Long id, BodyType updatedBodyType) {
        return bodyTypeRepository.findById(id)
                .map(bodyType -> {
                    updatedBodyType.setId(id);
                    return bodyTypeRepository.save(updatedBodyType);
                })
                .orElseThrow(() -> new RuntimeException("Body type not found with id: " + id));
    }

    /**
     * Delete a body type.
     *
     * @param id the body type ID to delete
     */
    public void deleteBodyType(Long id) {
        bodyTypeRepository.deleteById(id);
    }

    /**
     * Check if a body type exists.
     *
     * @param id the body type ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean bodyTypeExists(Long id) {
        return bodyTypeRepository.existsById(id);
    }
}
