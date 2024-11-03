package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

//import jakarta.persistence.*;
@Data
@Entity
@Table(name = "farms")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Farm name is required")
    private String name;
    @NotNull(message = "Farm owner name is required")
    private String surname;
    @NotNull(message = "Farm owner name is required")
    private String farmName;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Cow> cows;

    @ManyToMany
    @JoinTable(
            name = "farm_coops",
            joinColumns = @JoinColumn(name = "farm_id"),
            inverseJoinColumns = @JoinColumn(name = "coop_id")
    )
//    @JsonBackReference
    private List<Coop> coops;
}
