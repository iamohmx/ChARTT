package com.example.demo.controllers;

import com.example.demo.models.Cow;
import com.example.demo.services.CowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cows")
public class CowController {
    @Autowired
    private CowService cowService;

    @GetMapping("/")
    ResponseEntity<?> getAllCows() {
        return ResponseEntity.ok(cowService.getAllCows());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getCow(@PathVariable Long id) {
        return ResponseEntity.ok(cowService.getCow(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCow(@PathVariable Long id, @RequestBody Cow cow) {
        return ResponseEntity.ok(cowService.updateCow(id, cow));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCow(@PathVariable Long id) {
        cowService.deleteCow(id);
        return ResponseEntity.ok("Cow deleted");
    }
}
