package com.example.demo.services;

import com.example.demo.dto.FarmDto;
import com.example.demo.models.Coop;
import com.example.demo.models.Farm;
import com.example.demo.repositories.FarmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {
    @Autowired
    private FarmRepository farmRepository;


    public List<FarmDto> getAllFarms() {
        List<Farm> farms = farmRepository.findAll();
        return farms.stream().map(farm -> {
            FarmDto farmDto = new FarmDto();
            farmDto.setId(Long.valueOf(farm.getId()));
            farmDto.setFarmName(farm.getFarmName());
            farmDto.setName(farm.getName());
            farmDto.setSurname(farm.getSurname());
            return farmDto;
        }).collect(java.util.stream.Collectors.toList());

    }


    public Optional<FarmDto> getFarm(Long id) {
        Optional<Farm> farm = farmRepository.findById(id);
        if (farm.isPresent()) {
            FarmDto farmDto = new FarmDto();
            farmDto.setId(Long.valueOf(farm.get().getId()));
            farmDto.setFarmName(farm.get().getFarmName());
            farmDto.setName(farm.get().getName());
            farmDto.setSurname(farm.get().getSurname());
            return Optional.of(farmDto);
        }
        return Optional.empty();
    }

    public FarmDto updateFarm(Long id, Farm farm) {
        Optional<Farm> farmOptional = farmRepository.findById(id);
        if (farmOptional.isPresent()) {
            Farm updatedFarm = farmOptional.get();
            updatedFarm.setFarmName(farm.getFarmName());
            updatedFarm.setName(farm.getName());
            updatedFarm.setSurname(farm.getSurname());
            farmRepository.save(updatedFarm);
            FarmDto farmDto = new FarmDto();
            farmDto.setId(Long.valueOf(updatedFarm.getId()));
            farmDto.setFarmName(updatedFarm.getFarmName());
            farmDto.setName(updatedFarm.getName());
            farmDto.setSurname(updatedFarm.getSurname());
            return farmDto;
        }
        return null;
    }

    public void deleteFarm(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Farm not found"));
        farmRepository.delete(farm);

    }

    public FarmDto addFarm(Farm farm) {
        Farm savedFarm = farmRepository.save(farm);
        FarmDto farmDto = new FarmDto();
        farmDto.setId(Long.valueOf(savedFarm.getId()));
        farmDto.setFarmName(savedFarm.getFarmName());
        farmDto.setName(savedFarm.getName());
        farmDto.setSurname(savedFarm.getSurname());
        return farmDto;
    }
}
