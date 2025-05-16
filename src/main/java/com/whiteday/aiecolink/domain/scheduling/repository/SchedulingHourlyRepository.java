package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulingHourlyRepository extends JpaRepository<SchedulingHourly, Long> {
    List<SchedulingHourly> findBySchedulingPlan(SchedulingPlan plan);

}
