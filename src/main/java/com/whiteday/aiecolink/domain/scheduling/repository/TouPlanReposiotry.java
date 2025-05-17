package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.TouHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.TouPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TouPlanReposiotry extends JpaRepository<TouPlan, Long> {

    Optional<TouPlan> findByForecastDate(LocalDate date);

    List<TouHourly> findByTouId(Long touId);
}
