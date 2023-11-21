package com.marjane.controller;

import com.marjane.model.Center;
import com.marjane.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
@Validated
public class CenterController {

    private final CenterService centerService;

    @GetMapping
    public ResponseEntity<List<Center>> getAllCenters() {
        List<Center> centers = centerService.getAllCenters();
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenterById(@PathVariable Long id) {
        Center center = centerService.getCenterById(id);
        return ResponseEntity.ok(center);
    }

    @PostMapping
    public ResponseEntity<Center> createCenter(@Validated @RequestBody Map<String, String> request) {
        Center center = new Center(request.get("name"));
        Center createdCenter = centerService.createCenter(center);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCenter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Center> updateCenter(@PathVariable Long id, @Validated @RequestBody Map<String, String> request) {
        Center center = new Center(request.get("name"));
        Center updatedCenter = centerService.updateCenter(id, center);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
