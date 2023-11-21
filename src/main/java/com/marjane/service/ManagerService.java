package com.marjane.service;

import com.marjane.model.Manager;
import com.marjane.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Manager getManagerById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id: " + id));
    }

    public Manager createManager(Manager manager) {
        // Add any business logic or validation if needed
        return managerRepository.save(manager);
    }

    public Manager updateManager(Long id, Manager manager) {
        // Add any business logic or validation if needed
        Manager existingManager = getManagerById(id);
        existingManager.setEmail(manager.getEmail());
        existingManager.setPassword(manager.getPassword());
        return managerRepository.save(existingManager);
    }

    public void deleteManager(Long id) {
        // Add any business logic or validation if needed
        managerRepository.deleteById(id);
    }
}
