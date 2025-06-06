package com.whiteday.aiecolink.domain.station.repository;

import com.whiteday.aiecolink.domain.station.model.entity.Station;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
@Query("SELECT s FROM Station s WHERE s.name = :stationName")
    Optional<Station> findByStationName(String stationName);

    List<Station> findAllByUser(User user);
}


