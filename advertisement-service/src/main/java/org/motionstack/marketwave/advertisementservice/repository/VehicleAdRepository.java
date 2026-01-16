package org.motionstack.marketwave.advertisementservice.repository;

import org.motionstack.marketwave.advertisementservice.model.VehicleAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAdRepository extends JpaRepository<VehicleAd, Long> {
}