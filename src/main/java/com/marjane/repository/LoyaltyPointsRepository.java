package com.marjane.repository;

import com.marjane.model.LoyaltyPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, Long> {
    // Add custom queries if needed
}
