package com.marjane.controller;

import com.marjane.model.Admin;
import com.marjane.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Validated @RequestBody HashMap<String, String> request) {
        Admin admin = new Admin(request.get("name"), request.get("password"),request.get("email"));
        Admin createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @Validated @RequestBody HashMap<String, String> request) {
        Admin admin = new Admin(request.get("name"), request.get("password"),request.get("email"));
        Admin updatedAdmin = adminService.updateAdmin(id, admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
