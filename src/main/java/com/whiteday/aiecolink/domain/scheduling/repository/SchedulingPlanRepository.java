package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingPlan;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SchedulingPlanRepository extends JpaRepository<SchedulingPlan, Long> {

    Optional<SchedulingPlan> findTopByStationOrderByForecastDateDesc(Station station);

    boolean existsByStationAndForecastDate(Station station, LocalDate forecastDate);

    Optional<SchedulingPlan> findByStationAndForecastDate(Station station, LocalDate forecastDate);
}
