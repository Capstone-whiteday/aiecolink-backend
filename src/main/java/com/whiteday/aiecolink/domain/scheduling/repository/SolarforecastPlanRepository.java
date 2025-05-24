package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SolarforecastPlanRepository extends JpaRepository<SolarforecastPlan, Long> {

    Optional<SolarforecastPlan> findByRegion_RegionIdAndForecastDate(Long regionId, LocalDate localDate);
    Optional<SolarforecastPlan> findByForecastDate(LocalDate date);

}
