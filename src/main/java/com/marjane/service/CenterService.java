package com.marjane.service;

import com.marjane.dto.CenterDto;
import com.marjane.dto.CustomerDto;
import com.marjane.model.Center;
import com.marjane.model.Customer;
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

    public Center createCenter(CenterDto center) {
        Center updatedCenter = this.convertToEntity(center);
        return centerRepository.save(updatedCenter);
    }

    public Center updateCenter(Long id, CenterDto updatedCenter) {
        Center existingCenter = getCenterById(id);
        if (existingCenter != null) {
            existingCenter.setCityName(updatedCenter.getCityName());

            return centerRepository.save(existingCenter);
        }
        return null;
    }

    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }

    private Center convertToEntity(CenterDto centerDto) {
        Center entity = new Center();
        entity.setId(centerDto.getId());
        entity.setCityName(centerDto.getCityName());

        return entity;
    }
}
