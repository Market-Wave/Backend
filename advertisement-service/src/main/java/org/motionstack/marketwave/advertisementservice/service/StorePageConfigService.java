package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.StorePageConfig;
import org.motionstack.marketwave.advertisementservice.repository.StorePageConfigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for StorePageConfig entity.
 * Manages store page configurations including themes, layouts, and customization settings.
 */
@Service
@Transactional
public class StorePageConfigService {

    private final StorePageConfigRepository storePageConfigRepository;

    public StorePageConfigService(StorePageConfigRepository storePageConfigRepository) {
        this.storePageConfigRepository = storePageConfigRepository;
    }

    /**
     * Create a new store page configuration.
     *
     * @param config the configuration to create
     * @return the saved configuration
     */
    public StorePageConfig createConfig(StorePageConfig config) {
        return storePageConfigRepository.save(config);
    }

    /**
     * Retrieve a configuration by ID.
     *
     * @param id the configuration ID
     * @return Optional containing the configuration if found
     */
    @Transactional(readOnly = true)
    public Optional<StorePageConfig> getConfigById(Long id) {
        return storePageConfigRepository.findById(id);
    }

    /**
     * Retrieve configuration for a specific store.
     *
     * @param storeId the store ID
     * @return Optional containing the store's configuration if found
     */
    @Transactional(readOnly = true)
    public Optional<StorePageConfig> getConfigByStoreId(Long storeId) {
        return storePageConfigRepository.findAll().stream()
                .filter(config -> config.getStoreId().equals(storeId))
                .findFirst();
    }

    /**
     * Retrieve all configurations.
     *
     * @return list of all configurations
     */
    @Transactional(readOnly = true)
    public List<StorePageConfig> getAllConfigs() {
        return storePageConfigRepository.findAll();
    }

    /**
     * Update an existing configuration.
     *
     * @param id the configuration ID
     * @param updatedConfig the updated configuration data
     * @return the updated configuration
     */
    public StorePageConfig updateConfig(Long id, StorePageConfig updatedConfig) {
        return storePageConfigRepository.findById(id)
                .map(config -> {
                    updatedConfig.setId(id);
                    return storePageConfigRepository.save(updatedConfig);
                })
                .orElseThrow(() -> new RuntimeException("Configuration not found with id: " + id));
    }

    /**
     * Update theme settings for a store.
     *
     * @param storeId the store ID
     * @param themeName the theme name
     * @param themeVersion the theme version
     * @return the updated configuration
     */
    public StorePageConfig updateTheme(Long storeId, String themeName, String themeVersion) {
        return getConfigByStoreId(storeId)
                .map(config -> {
                    config.setThemeName(themeName);
                    config.setThemeVersion(themeVersion);
                    return storePageConfigRepository.save(config);
                })
                .orElseThrow(() -> new RuntimeException("Configuration not found for store: " + storeId));
    }

    /**
     * Update color scheme for a store.
     *
     * @param storeId the store ID
     * @param colors the color configuration JSON string
     * @return the updated configuration
     */
    public StorePageConfig updateColors(Long storeId, String colors) {
        return getConfigByStoreId(storeId)
                .map(config -> {
                    config.setColors(colors);
                    return storePageConfigRepository.save(config);
                })
                .orElseThrow(() -> new RuntimeException("Configuration not found for store: " + storeId));
    }

    /**
     * Update layout settings for a store.
     *
     * @param storeId the store ID
     * @param layout the layout configuration JSON string
     * @return the updated configuration
     */
    public StorePageConfig updateLayout(Long storeId, String layout) {
        return getConfigByStoreId(storeId)
                .map(config -> {
                    config.setLayout(layout);
                    return storePageConfigRepository.save(config);
                })
                .orElseThrow(() -> new RuntimeException("Configuration not found for store: " + storeId));
    }

    /**
     * Delete a configuration.
     *
     * @param id the configuration ID to delete
     */
    public void deleteConfig(Long id) {
        storePageConfigRepository.deleteById(id);
    }

    /**
     * Delete configuration for a specific store.
     *
     * @param storeId the store ID
     */
    public void deleteConfigByStoreId(Long storeId) {
        getConfigByStoreId(storeId).ifPresent(config -> 
            storePageConfigRepository.deleteById(config.getId())
        );
    }

    /**
     * Check if a configuration exists for a store.
     *
     * @param storeId the store ID
     * @return true if configuration exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean configExistsForStore(Long storeId) {
        return getConfigByStoreId(storeId).isPresent();
    }
}
