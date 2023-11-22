package com.marjane.service;

import com.marjane.dto.CenterDto;
import com.marjane.dto.ManagerDto;
import com.marjane.model.Center;
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

    public Manager createManager(ManagerDto manager) {
        Manager updatedManager = this.convertToEntity(manager);
        return managerRepository.save(updatedManager);
    }

    public Manager updateManager(Long id, ManagerDto manager) {
        Manager existingManager = getManagerById(id);
        existingManager.setEmail(manager.getEmail());
        existingManager.setPassword(manager.getPassword());
        return managerRepository.save(existingManager);
    }

    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }

    private Manager convertToEntity(ManagerDto managerDto) {
        Manager entity = new Manager();
        entity.setId(managerDto.getId());
        entity.setEmail(managerDto.getEmail());
        entity.setName(managerDto.getName());
        entity.setPassword(managerDto.getPassword());

        return entity;
    }
}
