package com.example.demo.controllers;

import com.example.demo.dto.CoopDto;
import com.example.demo.models.Coop;
import com.example.demo.services.CoopService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coops")
public class CoopController {
    @Autowired
    private CoopService coopService;

    @GetMapping("/")
    ResponseEntity<?> getAllCoops() {
        return ResponseEntity.ok(coopService.getAllCoops());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getCoop(@PathVariable Long id) {
        return ResponseEntity.ok(coopService.getCoop(id));
    }

    @PostMapping("/")
    ResponseEntity<?> addCoop(@RequestBody Coop coop) {
        CoopDto coopDto = coopService.addCoop(coop);
        return ResponseEntity.status(201).body(coopDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoop(@PathVariable Long id, @RequestBody Coop coop) {
        try {
            return ResponseEntity.ok(coopService.updateCoop(id, coop));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coop not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoop(@PathVariable Long id) {
        try {
            coopService.deleteCoop(id);
            return ResponseEntity.ok("Coop deleted");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coop not found");
        }
    }



}
