package com.whiteday.aiecolink.domain.battery.repository;

import com.whiteday.aiecolink.domain.battery.entity.Battery;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BatteryRepository extends JpaRepository<Battery,Long> {
    Optional<Battery> findByStationAndDate(Station station, LocalDate today);
}
