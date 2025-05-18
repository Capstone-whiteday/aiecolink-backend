package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.TouHourly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouHourlyRepository extends JpaRepository<TouHourly, Long> {
    List<TouHourly> findByTouPlan_TouId(Long touId);
}
