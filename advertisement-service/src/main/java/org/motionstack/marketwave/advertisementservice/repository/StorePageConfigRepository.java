package org.motionstack.marketwave.advertisementservice.repository;

import org.motionstack.marketwave.advertisementservice.model.StorePageConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorePageConfigRepository extends JpaRepository<StorePageConfig, Long> {
}