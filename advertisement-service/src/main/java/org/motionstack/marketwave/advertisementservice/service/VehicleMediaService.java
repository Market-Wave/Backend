package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.VehicleMedia;
import org.motionstack.marketwave.advertisementservice.repository.VehicleMediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for VehicleMedia entity.
 * Manages media files (images, videos) associated with vehicle advertisements.
 */
@Service
@Transactional
public class VehicleMediaService {

    private final VehicleMediaRepository vehicleMediaRepository;

    public VehicleMediaService(VehicleMediaRepository vehicleMediaRepository) {
        this.vehicleMediaRepository = vehicleMediaRepository;
    }

    /**
     * Add media to a vehicle ad.
     *
     * @param media the media to add
     * @return the saved media
     */
    public VehicleMedia addMedia(VehicleMedia media) {
        // Set default sort order if not provided
        if (media.getSortOrder() == null) {
            int maxOrder = getMediaForAd(media.getAdId()).stream()
                    .map(VehicleMedia::getSortOrder)
                    .max(Integer::compareTo)
                    .orElse(-1);
            media.setSortOrder(maxOrder + 1);
        }
        return vehicleMediaRepository.save(media);
    }

    /**
     * Retrieve media by ID.
     *
     * @param id the media ID
     * @return Optional containing the media if found
     */
    @Transactional(readOnly = true)
    public Optional<VehicleMedia> getMediaById(Long id) {
        return vehicleMediaRepository.findById(id);
    }

    /**
     * Retrieve all media for a specific vehicle ad.
     *
     * @param adId the ad ID
     * @return list of media sorted by sortOrder
     */
    @Transactional(readOnly = true)
    public List<VehicleMedia> getMediaForAd(Long adId) {
        return vehicleMediaRepository.findAll().stream()
                .filter(media -> media.getAdId().equals(adId))
                .sorted(Comparator.comparing(VehicleMedia::getSortOrder))
                .toList();
    }

    /**
     * Retrieve media by type for a specific ad.
     *
     * @param adId the ad ID
     * @param mediaType the media type (IMAGE, VIDEO, etc.)
     * @return list of media of the specified type
     */
    @Transactional(readOnly = true)
    public List<VehicleMedia> getMediaByType(Long adId, String mediaType) {
        return vehicleMediaRepository.findAll().stream()
                .filter(media -> media.getAdId().equals(adId) 
                        && media.getMediaType().equalsIgnoreCase(mediaType))
                .sorted(Comparator.comparing(VehicleMedia::getSortOrder))
                .toList();
    }

    /**
     * Retrieve media by view for a specific ad.
     *
     * @param adId the ad ID
     * @param mediaView the media view (EXTERIOR, INTERIOR, DASHBOARD, etc.)
     * @return list of media of the specified view
     */
    @Transactional(readOnly = true)
    public List<VehicleMedia> getMediaByView(Long adId, String mediaView) {
        return vehicleMediaRepository.findAll().stream()
                .filter(media -> media.getAdId().equals(adId) 
                        && media.getMediaView().equalsIgnoreCase(mediaView))
                .sorted(Comparator.comparing(VehicleMedia::getSortOrder))
                .toList();
    }

    /**
     * Update media details.
     *
     * @param id the media ID
     * @param updatedMedia the updated media data
     * @return the updated media
     */
    public VehicleMedia updateMedia(Long id, VehicleMedia updatedMedia) {
        return vehicleMediaRepository.findById(id)
                .map(media -> {
                    updatedMedia.setId(id);
                    return vehicleMediaRepository.save(updatedMedia);
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
    public VehicleMedia updateSortOrder(Long id, Integer sortOrder) {
        return vehicleMediaRepository.findById(id)
                .map(media -> {
                    media.setSortOrder(sortOrder);
                    return vehicleMediaRepository.save(media);
                })
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
    }

    /**
     * Delete media.
     *
     * @param id the media ID to delete
     */
    public void deleteMedia(Long id) {
        vehicleMediaRepository.deleteById(id);
    }

    /**
     * Delete all media for a specific ad.
     *
     * @param adId the ad ID
     */
    public void deleteAllMediaForAd(Long adId) {
        List<VehicleMedia> mediaList = getMediaForAd(adId);
        mediaList.forEach(media -> vehicleMediaRepository.deleteById(media.getId()));
    }
}
