package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.StoreMedia;
import org.motionstack.marketwave.advertisementservice.repository.StoreMediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for StoreMedia entity.
 * Manages media files (images, videos) associated with stores.
 */
@Service
@Transactional
public class StoreMediaService {

    private final StoreMediaRepository storeMediaRepository;

    public StoreMediaService(StoreMediaRepository storeMediaRepository) {
        this.storeMediaRepository = storeMediaRepository;
    }

    /**
     * Add media to a store.
     *
     * @param media the media to add
     * @return the saved media
     */
    public StoreMedia addMedia(StoreMedia media) {
        // Set default sort order if not provided
        if (media.getSortOrder() == null) {
            int maxOrder = getMediaForStore(media.getStoreId()).stream()
                    .map(StoreMedia::getSortOrder)
                    .max(Integer::compareTo)
                    .orElse(-1);
            media.setSortOrder(maxOrder + 1);
        }
        return storeMediaRepository.save(media);
    }

    /**
     * Retrieve media by ID.
     *
     * @param id the media ID
     * @return Optional containing the media if found
     */
    @Transactional(readOnly = true)
    public Optional<StoreMedia> getMediaById(Long id) {
        return storeMediaRepository.findById(id);
    }

    /**
     * Retrieve all media for a specific store.
     *
     * @param storeId the store ID
     * @return list of media sorted by sortOrder
     */
    @Transactional(readOnly = true)
    public List<StoreMedia> getMediaForStore(Long storeId) {
        return storeMediaRepository.findAll().stream()
                .filter(media -> media.getStoreId().equals(storeId))
                .sorted(Comparator.comparing(StoreMedia::getSortOrder))
                .toList();
    }

    /**
     * Retrieve media by type for a specific store.
     *
     * @param storeId the store ID
     * @param mediaType the media type (IMAGE, VIDEO, LOGO, BANNER, etc.)
     * @return list of media of the specified type
     */
    @Transactional(readOnly = true)
    public List<StoreMedia> getMediaByType(Long storeId, String mediaType) {
        return storeMediaRepository.findAll().stream()
                .filter(media -> media.getStoreId().equals(storeId) 
                        && media.getMediaType().equalsIgnoreCase(mediaType))
                .sorted(Comparator.comparing(StoreMedia::getSortOrder))
                .toList();
    }

    /**
     * Retrieve media by view for a specific store.
     *
     * @param storeId the store ID
     * @param mediaView the media view (STOREFRONT, INTERIOR, LOGO, BANNER, etc.)
     * @return list of media of the specified view
     */
    @Transactional(readOnly = true)
    public List<StoreMedia> getMediaByView(Long storeId, String mediaView) {
        return storeMediaRepository.findAll().stream()
                .filter(media -> media.getStoreId().equals(storeId) 
                        && media.getMediaView().equalsIgnoreCase(mediaView))
                .sorted(Comparator.comparing(StoreMedia::getSortOrder))
                .toList();
    }

    /**
     * Update media details.
     *
     * @param id the media ID
     * @param updatedMedia the updated media data
     * @return the updated media
     */
    public StoreMedia updateMedia(Long id, StoreMedia updatedMedia) {
        return storeMediaRepository.findById(id)
                .map(media -> {
                    updatedMedia.setId(id);
                    return storeMediaRepository.save(updatedMedia);
                })
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
    }

    /**
     * Update the sort order of media items.
     *
     * @param id the media ID
     * @param sortOrder the new sort order
     * @return the updated media
     */
    public StoreMedia updateSortOrder(Long id, Integer sortOrder) {
        return storeMediaRepository.findById(id)
                .map(media -> {
                    media.setSortOrder(sortOrder);
                    return storeMediaRepository.save(media);
                })
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
    }

    /**
     * Delete media.
     *
     * @param id the media ID to delete
     */
    public void deleteMedia(Long id) {
        storeMediaRepository.deleteById(id);
    }

    /**
     * Delete all media for a specific store.
     *
     * @param storeId the store ID
     */
    public void deleteAllMediaForStore(Long storeId) {
        List<StoreMedia> mediaList = getMediaForStore(storeId);
        mediaList.forEach(media -> storeMediaRepository.deleteById(media.getId()));
    }
}
