package com.marjane.repository;

import com.marjane.model.CustomerPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPurchaseRepository extends JpaRepository<CustomerPurchase, Long> {
    // Add custom queries if needed
}
