package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.AdCategory;
import org.motionstack.marketwave.advertisementservice.repository.AdCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for AdCategory entity.
 * Manages advertisement categories.
 */
@Service
@Transactional
public class AdCategoryService {

    private final AdCategoryRepository adCategoryRepository;

    public AdCategoryService(AdCategoryRepository adCategoryRepository) {
        this.adCategoryRepository = adCategoryRepository;
    }

    /**
     * Create a new ad category.
     *
     * @param category the category to create
     * @return the saved category
     */
    public AdCategory createCategory(AdCategory category) {
        return adCategoryRepository.save(category);
    }

    /**
     * Retrieve a category by ID.
     *
     * @param id the category ID
     * @return Optional containing the category if found
     */
    @Transactional(readOnly = true)
    public Optional<AdCategory> getCategoryById(Long id) {
        return adCategoryRepository.findById(id);
    }

    /**
     * Retrieve all categories.
     *
     * @return list of all categories
     */
    @Transactional(readOnly = true)
    public List<AdCategory> getAllCategories() {
        return adCategoryRepository.findAll();
    }

    /**
     * Update an existing category.
     *
     * @param id the category ID
     * @param updatedCategory the updated category data
     * @return the updated category
     */
    public AdCategory updateCategory(Long id, AdCategory updatedCategory) {
        return adCategoryRepository.findById(id)
                .map(category -> {
                    updatedCategory.setId(id);
                    return adCategoryRepository.save(updatedCategory);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    /**
     * Delete a category.
     *
     * @param id the category ID to delete
     */
    public void deleteCategory(Long id) {
        adCategoryRepository.deleteById(id);
    }

    /**
     * Check if a category exists.
     *
     * @param id the category ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean categoryExists(Long id) {
        return adCategoryRepository.existsById(id);
    }
}
