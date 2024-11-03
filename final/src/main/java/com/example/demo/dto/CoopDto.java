package com.example.demo.dto;

import com.example.demo.models.Coop;
import lombok.Data;

@Data
public class CoopDto {
    private Long Id;
    private String coopName;
    private String Address;
    private String Province;

    public static Coop convertToEntity(Coop existingCoop) {
        Coop coop = new Coop();
        coop.setId(existingCoop.getId());
        coop.setCoopName(existingCoop.getCoopName());
        coop.setAddress(existingCoop.getAddress());
        coop.setProvince(existingCoop.getProvince());
        return coop;
    }
}
