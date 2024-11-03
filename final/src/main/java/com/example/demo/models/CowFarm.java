package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cow_farms")
public class CowFarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cow_id")
    private Cow cow;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;
}
