package com.marjane.service;

import com.marjane.model.Center;
import com.marjane.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public Center getCenterById(Long id) {
        return centerRepository.findById(id).orElse(null);
    }

    public Center createCenter(Center center) {
        return centerRepository.save(center);
    }

    public Center updateCenter(Long id, Center updatedCenter) {
        Center existingCenter = getCenterById(id);
        if (existingCenter != null) {
            existingCenter.setName(updatedCenter.getName());
            // Add other fields as needed
            return centerRepository.save(existingCenter);
        }
        return null;
    }

    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }
}
