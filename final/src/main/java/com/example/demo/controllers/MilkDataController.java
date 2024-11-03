package com.example.demo.controllers;

import com.example.demo.dto.MilkingDataDTO;
import com.example.demo.repositories.MilkingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/milkdata")
public class MilkDataController {

    @Autowired
    MilkingDataRepository milkingDataRepository;

    @GetMapping("/perday")
    public ResponseEntity<List<MilkingDataDTO>> getMilkDataPerDay() {
        List<MilkingDataDTO> data = milkingDataRepository.findMilkDataGroupedByDay();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/permonth")
    public ResponseEntity<List<MilkingDataDTO>> getMilkDataPerMonth() {
        List<MilkingDataDTO> data = milkingDataRepository.findMilkDataGroupedByMonth();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/peryear")
    public ResponseEntity<List<MilkingDataDTO>> getMilkDataPerYear() {
        List<MilkingDataDTO> data = milkingDataRepository.findMilkDataGroupedByYear();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/perfarm")
    public ResponseEntity<List<MilkingDataDTO>> getMilkDataPerFarm() {
        List<MilkingDataDTO> data = milkingDataRepository.findMilkDataGroupedByFarm();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/percoop")
    public ResponseEntity<List<MilkingDataDTO>> getMilkDataPerCoop() {
        List<MilkingDataDTO> data = milkingDataRepository.findMilkDataGroupedByCoop();
        return ResponseEntity.ok(data);
    }
}
