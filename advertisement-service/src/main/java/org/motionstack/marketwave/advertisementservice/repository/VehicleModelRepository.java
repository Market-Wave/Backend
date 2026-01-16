package org.motionstack.marketwave.advertisementservice.repository;

import org.motionstack.marketwave.advertisementservice.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
}