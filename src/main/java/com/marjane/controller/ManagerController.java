package com.marjane.controller;

import com.marjane.model.Manager;
import com.marjane.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
@Validated
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        List<Manager> managers = managerService.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        Manager manager = managerService.getManagerById(id);
        return ResponseEntity.ok(manager);
    }

    @PostMapping
    public ResponseEntity<Manager> createManager(@Validated @RequestBody HashMap<String, String> request) {
        Manager manager = Manager.builder()
                .email(request.get("email"))
                .password(request.get("password"))
                .build();

        Manager createdManager = managerService.createManager(manager);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdManager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable Long id, @Validated @RequestBody HashMap<String, String> request) {
        Manager manager = Manager.builder()
                .email(request.get("email"))
                .password(request.get("password"))
                .build();

        Manager updatedManager = managerService.updateManager(id, manager);
        return ResponseEntity.ok(updatedManager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}
