package com.marjane.repository;

import com.marjane.model.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {
    // Add custom queries if needed
}
