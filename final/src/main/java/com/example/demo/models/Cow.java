package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "cows")
public class Cow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Breed is required")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToMany(mappedBy = "cows", cascade = CascadeType.ALL)
    private List<MilkingData> milkingDataList;

    @ManyToMany
    @JoinTable(
            name = "cow_farms",
            joinColumns = @JoinColumn(name = "cow_id"),
            inverseJoinColumns = @JoinColumn(name = "farm_id")
    )
//    @JsonBackReference
    private List<Farm> farms;

}
