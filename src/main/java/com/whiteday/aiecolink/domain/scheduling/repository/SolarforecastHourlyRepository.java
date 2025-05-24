package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastHourly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolarforecastHourlyRepository extends JpaRepository<SolarforecastHourly, Long> {
    List<SolarforecastHourly> findBySolarforecastPlan_SolarforecastId(Long solarforecastId);
}

