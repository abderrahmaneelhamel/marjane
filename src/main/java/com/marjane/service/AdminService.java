package com.marjane.service;

import com.marjane.model.Admin;
import com.marjane.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin admin) {
        // Add any business logic or validation if needed
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPassword(admin.getPassword());
        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long id) {
        // Add any business logic or validation if needed
        adminRepository.deleteById(id);
    }
}
