package com.whiteday.aiecolink.domain.station.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StationSummaryRes {
    private Long stationId;
    private String name;
    private String location;
    private Status status;
}
