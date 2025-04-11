package com.whiteday.aiecolink.domain.station.model.request;

import com.whiteday.aiecolink.domain.user.model.User;
import lombok.Getter;

@Getter
public class StationRegisterReq {
    private String stationName;
    private String location;
    private User user;
}
