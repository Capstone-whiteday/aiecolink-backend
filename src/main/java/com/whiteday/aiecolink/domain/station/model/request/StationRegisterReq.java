package com.whiteday.aiecolink.domain.station.model.request;

import com.whiteday.aiecolink.domain.station.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StationRegisterReq {
    private String stationName;
    private String location;
    private Status status;
    private String description;
    private Long regionId;
}
