package com.example.demo.repositories;

import com.example.demo.dto.MilkingDataDTO;
import com.example.demo.models.MilkingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilkingDataRepository extends JpaRepository<MilkingData, Long> {

    @Query("SELECT new com.example.demo.dto.MilkingDataDTO(m.id, m.dmy, m.milkKg, m.cows.name, m.farm.farmName, m.coop.coopName) " +
            "FROM MilkingData m WHERE DAY(m.dmy) = DAY(CURRENT_DATE)")
    List<MilkingDataDTO> findMilkDataGroupedByDay();

    @Query("SELECT new com.example.demo.dto.MilkingDataDTO(m.id, m.dmy, m.milkKg, m.cows.name, m.farm.farmName, m.coop.coopName) " +
            "FROM MilkingData m WHERE YEAR(m.dmy) = YEAR(CURRENT_DATE)")
    List<MilkingDataDTO> findMilkDataGroupedByYear();

    // แก้ไข query สำหรับรายเดือน
    @Query("SELECT new com.example.demo.dto.MilkingDataDTO(m.id, m.dmy, m.milkKg, m.cows.name, m.farm.farmName, m.coop.coopName) " +
            "FROM MilkingData m WHERE MONTH(m.dmy) = MONTH(CURRENT_DATE) AND YEAR(m.dmy) = YEAR(CURRENT_DATE)")
    List<MilkingDataDTO> findMilkDataGroupedByMonth();

    @Query("SELECT new com.example.demo.dto.MilkingDataDTO(m.id, m.dmy, m.milkKg, m.cows.name, m.farm.farmName, m.coop.coopName) " +
            "FROM MilkingData m GROUP BY m.farm.id")
    List<MilkingDataDTO> findMilkDataGroupedByFarm();

    @Query("SELECT new com.example.demo.dto.MilkingDataDTO(m.id, m.dmy, m.milkKg, m.cows.name, m.farm.farmName, m.coop.coopName) " +
            "FROM MilkingData m GROUP BY m.coop.id")
    List<MilkingDataDTO> findMilkDataGroupedByCoop();
}
