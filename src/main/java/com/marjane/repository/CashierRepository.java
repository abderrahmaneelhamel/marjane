package com.marjane.repository;

import com.marjane.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, Long> {
    // Add custom queries if needed
}
