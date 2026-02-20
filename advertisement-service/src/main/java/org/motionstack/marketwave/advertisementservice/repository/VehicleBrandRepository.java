package org.motionstack.marketwave.advertisementservice.repository;

import org.motionstack.marketwave.advertisementservice.model.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Long> {
    boolean existsByName(String name);
}