package com.example.demo.controllers;

import com.example.demo.models.Farm;
import com.example.demo.services.FarmService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping("/")
    ResponseEntity<?> getAllFarms() {
    return ResponseEntity.ok(farmService.getAllFarms());

    }

    @GetMapping("/{id}")
    ResponseEntity<?> getFarm(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarm(id));
    }

    @PostMapping("/")
    ResponseEntity<?> addFarm(@RequestBody Farm farm) {
        return ResponseEntity.status(201).body(farmService.addFarm(farm));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateFarm(@PathVariable Long id, @RequestBody Farm farm) {
        try {
            return ResponseEntity.ok(farmService.updateFarm(id, farm));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm not found");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFarm(@PathVariable Long id) {
        try {
            farmService.deleteFarm(id);
            return ResponseEntity.ok("Farm deleted");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm not found");
        }
    }
}
