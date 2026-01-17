package org.motionstack.marketwave.advertisementservice.service;

import org.motionstack.marketwave.advertisementservice.model.VehicleAdLike;
import org.motionstack.marketwave.advertisementservice.repository.VehicleAdLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for VehicleAdLike entity.
 * Manages user likes on vehicle advertisements.
 */
@Service
@Transactional
public class VehicleAdLikeService {

    private final VehicleAdLikeRepository vehicleAdLikeRepository;

    public VehicleAdLikeService(VehicleAdLikeRepository vehicleAdLikeRepository) {
        this.vehicleAdLikeRepository = vehicleAdLikeRepository;
    }

    /**
     * Add a like to a vehicle ad.
     *
     * @param userId the user ID
     * @param adId the ad ID
     * @return the saved like
     */
    public VehicleAdLike likeAd(UUID userId, Long adId) {
        // Check if already liked
        Optional<VehicleAdLike> existing = findLike(userId, adId);
        if (existing.isPresent()) {
            return existing.get();
        }
        
        VehicleAdLike like = new VehicleAdLike();
        like.setUserId(userId);
        like.setAdId(adId);
        return vehicleAdLikeRepository.save(like);
    }

    /**
     * Remove a like from a vehicle ad.
     *
     * @param userId the user ID
     * @param adId the ad ID
     */
    public void unlikeAd(UUID userId, Long adId) {
        findLike(userId, adId).ifPresent(like -> 
            vehicleAdLikeRepository.deleteById(like.getId())
        );
    }

    /**
     * Find a specific like.
     *
     * @param userId the user ID
     * @param adId the ad ID
     * @return Optional containing the like if found
     */
    @Transactional(readOnly = true)
    public Optional<VehicleAdLike> findLike(UUID userId, Long adId) {
        return vehicleAdLikeRepository.findAll().stream()
                .filter(like -> like.getUserId().equals(userId) && like.getAdId().equals(adId))
                .findFirst();
    }

    /**
     * Check if a user has liked an ad.
     *
     * @param userId the user ID
     * @param adId the ad ID
     * @return true if liked, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean hasUserLikedAd(UUID userId, Long adId) {
        return findLike(userId, adId).isPresent();
    }

    /**
     * Get all likes for a specific ad.
     *
     * @param adId the ad ID
     * @return list of likes for the ad
     */
    @Transactional(readOnly = true)
    public List<VehicleAdLike> getLikesForAd(Long adId) {
        return vehicleAdLikeRepository.findAll().stream()
                .filter(like -> like.getAdId().equals(adId))
                .toList();
    }

    /**
     * Get the total like count for an ad.
     *
     * @param adId the ad ID
     * @return total number of likes
     */
    @Transactional(readOnly = true)
    public long getLikeCount(Long adId) {
        return getLikesForAd(adId).size();
    }

    /**
     * Get all ads liked by a user.
     *
     * @param userId the user ID
     * @return list of ad IDs liked by the user
     */
    @Transactional(readOnly = true)
    public List<Long> getUserLikedAds(UUID userId) {
        return vehicleAdLikeRepository.findAll().stream()
                .filter(like -> like.getUserId().equals(userId))
                .map(VehicleAdLike::getAdId)
                .toList();
    }
}
