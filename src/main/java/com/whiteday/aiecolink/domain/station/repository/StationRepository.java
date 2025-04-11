package com.whiteday.aiecolink.domain.station.repository;

import com.whiteday.aiecolink.domain.station.model.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
