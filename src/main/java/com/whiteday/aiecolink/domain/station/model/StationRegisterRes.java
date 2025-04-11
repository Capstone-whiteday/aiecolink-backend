package com.whiteday.aiecolink.domain.station.model;

import com.whiteday.aiecolink.domain.station.model.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationRegisterRes {
    private Long stationId;
    private String name;

    public StationRegisterRes toDto(Station station){
        return StationRegisterRes.builder()
                .stationId(station.getStationId())
                .name(station.getName())
                .build();
    }
}
