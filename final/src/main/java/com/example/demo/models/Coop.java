package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "coops")
public class Coop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Coop Name is required")
    private String coopName;
    @NotNull(message = "Address is required")
    private String address;
    @NotNull(message = "Province is required")
    private String province;

    @ManyToMany(mappedBy = "coops")
//    @JsonManagedReference
    private List<Farm> farms;
}

