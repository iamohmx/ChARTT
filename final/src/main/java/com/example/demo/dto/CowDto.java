package com.example.demo.dto;

import com.example.demo.models.Farm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CowDto {
    private Long id;
    private String name;
    private String birthDate;
    private Long farmId;

    List<Farm> farms;

    public void setFarms(@NotNull(message = "Farm owner name is required") String farmName) {

    }
}
