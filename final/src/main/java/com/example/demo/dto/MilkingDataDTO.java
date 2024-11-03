package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MilkingDataDTO {
    private Long id;
    private LocalDate date;
    private Double milkKg;
    private String cowName;
    private String farmName;
    private String coopName;

    public MilkingDataDTO(Long id, LocalDate date, Double milkKg, String cowName, String farmName, String coopName) {
        this.id = id;
        this.date = date;
        this.milkKg = milkKg;
        this.cowName = cowName;
        this.farmName = farmName;
        this.coopName = coopName;
    }
}
