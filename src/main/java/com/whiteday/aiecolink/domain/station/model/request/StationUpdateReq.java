package com.whiteday.aiecolink.domain.station.model.request;

import com.whiteday.aiecolink.domain.station.model.Status;
import lombok.Getter;

@Getter
public class StationUpdateReq {
    private String name;
    private Status status;
    private String description;
}
