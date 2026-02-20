package org.motionstack.marketwave.advertisementservice.repository;

import org.motionstack.marketwave.advertisementservice.model.AdCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdCategoryRepository extends JpaRepository<AdCategory, Long> {
    boolean existsByName(String name);
}