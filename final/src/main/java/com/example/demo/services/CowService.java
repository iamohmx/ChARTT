package com.example.demo.services;

import com.example.demo.dto.CowDto;
import com.example.demo.models.Cow;
import com.example.demo.repositories.CowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CowService {
    private final CowRepository cowRepository;

    public CowService(CowRepository cowRepository) {
        this.cowRepository = cowRepository;
    }

    public List<CowDto> getAllCows() {
        List<Cow> cows = cowRepository.findAll();
        return cows.stream().map(cow -> {
            CowDto cowDto = new CowDto();
            cowDto.setId(Long.valueOf(cow.getId()));
            cowDto.setName(cow.getName());
            cowDto.setBirthDate(String.valueOf(cow.getBirthDate()));
            cowDto.setFarmId(Long.valueOf(cow.getFarm().getId()));
            cowDto.setFarms(cow.getFarm().getFarmName());
            return cowDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    public Optional<CowDto> getCow(Long id) {
        Optional<Cow> cow = cowRepository.findById(id);
        if (cow.isPresent()) {
            CowDto cowDto = new CowDto();
            cowDto.setId(Long.valueOf(cow.get().getId()));
            cowDto.setName(cow.get().getName());
            cowDto.setBirthDate(String.valueOf(cow.get().getBirthDate()));
            cowDto.setFarmId(Long.valueOf(cow.get().getFarm().getId()));
            cowDto.setFarms(cow.get().getFarm().getFarmName());
            return Optional.of(cowDto);
        }
        return Optional.empty();
    }

    public CowDto updateCow(Long id, Cow cow) {
        Optional<Cow> cowOptional = cowRepository.findById(id);
        if (cowOptional.isPresent()) {
            Cow updatedCow = cowOptional.get();
            updatedCow.setName(cow.getName());
            updatedCow.setBirthDate(cow.getBirthDate());
            updatedCow.setFarm(cow.getFarm());
            cowRepository.save(updatedCow);
            CowDto cowDto = new CowDto();
            cowDto.setId(Long.valueOf(updatedCow.getId()));
            cowDto.setName(updatedCow.getName());
            cowDto.setBirthDate(String.valueOf(updatedCow.getBirthDate()));
            cowDto.setFarmId(Long.valueOf(updatedCow.getFarm().getId()));
            cowDto.setFarms(updatedCow.getFarm().getFarmName());
            return cowDto;
        }
        return null;
    }

    public void deleteCow(Long id) {
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cow not found"));
        cowRepository.delete(cow);
    }
}
