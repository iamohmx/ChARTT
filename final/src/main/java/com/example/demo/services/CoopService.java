package com.example.demo.services;

import com.example.demo.dto.CoopDto;
import com.example.demo.models.Coop;
import com.example.demo.models.Farm;
import com.example.demo.repositories.CoopRepository;
import com.example.demo.repositories.FarmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoopService {
    @Autowired
    private CoopRepository coopRepository;

    @Autowired
    private FarmRepository farmRepository;

    public CoopService(CoopRepository coopRepository) {
        this.coopRepository = coopRepository;
    }

    public List<CoopDto> getAllCoops() {
        List<Coop> coops = coopRepository.findAll();
        return coops.stream()
                .map(coop -> {
                    CoopDto coopDto = new CoopDto();
                    coopDto.setId(Long.valueOf(coop.getId()));
                    coopDto.setCoopName(coop.getCoopName());
                    coopDto.setAddress(coop.getAddress());
                    coopDto.setProvince(coop.getProvince());
                    return coopDto;
                })
                .collect(Collectors.toList());
    }

    public Optional<Optional<CoopDto>> getCoop(Long id) {
        Optional<Coop> coop = coopRepository.findById(id);
        if (coop.isPresent()) {
            return coop.stream().map(coop1 -> {
                CoopDto coopDto = new CoopDto();
                coopDto.setId(Long.valueOf(coop1.getId()));
                coopDto.setCoopName(coop1.getCoopName());
                coopDto.setAddress(coop1.getAddress());
                coopDto.setProvince(coop1.getProvince());
                return Optional.of(coopDto);
            }).findFirst();
        }
        return Optional.empty();
    }

    public CoopDto addCoop(Coop coop) {
        Coop savedCoop = coopRepository.save(coop);
        CoopDto coopDto = new CoopDto();
        coopDto.setId(Long.valueOf(savedCoop.getId()));
        coopDto.setCoopName(savedCoop.getCoopName());
        coopDto.setAddress(savedCoop.getAddress());
        coopDto.setProvince(savedCoop.getProvince());
        return coopDto;
    }

    public Coop updateCoop(Long id, Coop coopDetails) {
        Coop existingCoop = coopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coop with ID " + id + " not found"));

        existingCoop.setCoopName(coopDetails.getCoopName());
        existingCoop.setAddress(coopDetails.getAddress());
        existingCoop.setProvince(coopDetails.getProvince());

        // อัปเดตความสัมพันธ์กับ Farms ถ้าจำเป็น
        if (coopDetails.getFarms() != null) {
            List<Farm> farms = farmRepository.findAllById(
                    coopDetails.getFarms().stream().map(farm -> Long.valueOf(farm.getId())).collect(Collectors.toList())
            );
            existingCoop.setFarms(farms);
        }
        coopRepository.save(existingCoop);
        return CoopDto.convertToEntity(existingCoop);
    }

    public void deleteCoop(Long id) {
        Coop coop = coopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coop with ID " + id + " not found"));
        coopRepository.delete(coop);
    }
}