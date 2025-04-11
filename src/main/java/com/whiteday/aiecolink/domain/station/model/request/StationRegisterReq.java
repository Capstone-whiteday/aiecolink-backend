package com.whiteday.aiecolink.domain.station.model.request;

import com.whiteday.aiecolink.domain.user.model.UserReq;
import lombok.Getter;

@Getter
public class StationRegisterReq {
    private UserReq user;
    private String stationName;
    private String location;
}
