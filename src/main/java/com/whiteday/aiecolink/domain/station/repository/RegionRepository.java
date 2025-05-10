package com.whiteday.aiecolink.domain.station.repository;

import com.whiteday.aiecolink.domain.station.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {
}
